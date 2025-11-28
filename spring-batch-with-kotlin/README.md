# Study Spring Batch with kotlin

バッチアプリケーションでは、このように `SpringApplication.run()` の結果を `System.exit()` で処理することが推奨されます。バッチジョブの成功/失敗の状態を exit code として外部システムに伝達できるため、実務でのバッチ監視と制御に不可欠だからです。
```kotlin
exitProcess(SpringApplication.exit(SpringApplication.run(SpringBatchWithKotlinApplication::class.java, *args)))
```


`DefaultBatchConfiguration` は Spring Batch 5 から導入された基本設定クラスで、`JobRepository`、`JobLauncher` など Spring Batch のコアコンポーネントを自動的に構成してくれます。

Spring Batch は `Job` と `Step` の実行情報（メタデータ）をデータベースに保存します。したがって、メタデータのための `DataSource` 設定が必要です。

メタデータの保存やバッチジョブの実行など、Spring Batch の **すべての作業はトランザクション内で処理**されます。このために、バッチコアコンポーネントと私たちの Job で共通して使用する `PlatformTransactionManager` Bean を登録する必要があります。

## Job

Spring Batch は `Job` を実行する際、Spring コンテナから `Job` Bean を探して実行するため、`Job` は必ず Bean として登録するようにします。
```kotlin
@Bean
fun systemTerminationSimulationJob(): Job {
    return JobBuilder("systemTerminationSimulationJob", jobRepository)
        .start { enterWorldStep() }
        .next { meetNPCStep() }
        .next { defeatProcessStep() }
        .next { completeQuestStep() }
        .build()
}
```
Job の構成を見ると、start() メソッドで最初に実行する Step を指定し、next() メソッドを通じて順次実行される次の Step を定義します。このように定義された Step は、前の Step が正常に完了した後にのみ実行されます。例えば、enterWorldStep が完了してから meetNPCStep が実行され、その次に defeatProcessStep、completeQuestStep の順に進みます。もし前の Step が失敗した場合、次の Step は実行されません。



## Step

```kotlin
@Bean
fun enterWorldStep(): Step {
    return StepBuilder("enterWorldStep", jobRepository)
        .tasklet({ contribution, chunkContext ->
            println("welcome to spring batch")
            RepeatStatus.FINISHED
        }, transactionManager)
        .build()
}
```

StepBuilder の最初のパラメータとしては Step の識別子を指定しますが、これは後に Job と Step の状態を追跡し制御する際に使用されます。また、JobBuilder と同様に StepBuilder にも JobRepository を渡し、Step の実行情報が管理されるようにします。

Step の実際の動作は tasklet() メソッドを通じて定義されます。

Spring BatchのStepは、主に次の2つの処理モデルに分けられます。

- チャンク指向処理 (Chunk-Oriented Processing) aka COP
- タスクレット指向処理 (Tasklet-Oriented Processing) aka TOP

### Tasklet

タスクレット（Tasklet）指向処理モデルは、Spring Batchにおける最も基本的なStep実装方式であり、比較的複雑ではない単純な作業を実行する際に使用されます。

Spring Batchは、Taskletの execute() が呼び出されるたびに新しいトランザクションを開始し、execute() の実行が終了して RepeatStatus が返されると、そのトランザクションをコミットします。

しかし、一般的にTaskletでDBトランザクション管理が必要なケースはそれほど多くありません。すべてのTaskletがデータベース作業を含むわけではないからです。例えば、ファイルの整理、外部APIの呼び出し、単純な通知の送信といった作業であれば、DBトランザクションを考慮する必要はありません。

このような場合、実際のDBコネクションを管理する一般的な PlatformTransactionManager 実装体を使用する代わりに、ResourcelessTransactionManager というオプションを検討できます。

ResourcelessTransactionManager は、no-op（何もしない）方式で動作する PlatformTransactionManager 実装体であり、これを使用することで不要なDBトランザクション処理を省略できます。

ResourcelessTransactionManager をStepに適用するには、次のように ResourcelessTransactionManager インスタンスを tasklet() メソッドに渡します。

```java
@Bean
public Step zombieCleanupStep() {
    return new StepBuilder("zombieCleanupStep", jobRepository)
            .tasklet(zombieProcessCleanupTasklet(), new ResourcelessTransactionManager())
            .build();
}
```

### Chunk

チャンク（Chunk）とは、データを一定の単位で分割した塊のことを指します。Spring Batchでデータ基盤の処理方式を「チャンク指向処理」と呼ぶ理由は、読み込み（Read）、処理（Process）、書き込み（Write）の作業を、一定のサイズに分割されたデータ塊（チャンク）を対象に行うためです。
Spring Batchに触れて扱うことになる大部分のバッチ作業、特にデータを扱う作業は、読み込み → 処理 → 書き込みという共通のパターンを持っています。Spring Batchもデータを扱う際はこのパターンに従います。そして、この方式をSpring Batchではチャンク指向処理と呼びます。
では、このパターンはSpring Batchでどのように具体化されるのでしょうか？
Spring Batchはこれを3つのコンポーネントで実装しています。
すなわち、ItemReader、ItemProcessor、ItemWriter です。
#### ItemReader

```java
public interface ItemReader<T> {
    T read() throws Exception, 
        UnexpectedInputException, 
        ParseException, 
        NonTransientResourceException;
}
```
read() メソッドの戻り値の型に注目してください。read() メソッドはアイテムを1つずつ返します。ここでアイテムとは、ファイルの1行、またはデータベースの1行（row）に相当するデータ1件を意味します。
ItemReader が null を返すときがチャンク指向処理Stepの終了時点であるという点を必ず記憶してください。これはSpring BatchがStepの完了を判断する核心的な条件です。

#### ItemProcessor

```java
public interface ItemProcessor<I, O> {
    O process(I item) throws Exception;
}
```

process() メソッドが null を返すと、その入力データは処理フローから除外されます。言い換えれば、ItemWriter に渡されません。有効でないデータや処理する必要のないデータを除外する際に使用します。
ItemProcessor は省略可能です。つまり、Stepがデータを読み込んで即座に書き込むように構成することもできます。

#### ItemWriter

```java
public interface ItemWriter<T> {
    void write(List<? extends T> items) throws Exception;
}
```
`ItemWriter` は `ItemProcessor` が生成した結果を受け取り、望ましい方式で最終的に保存/出力します。（データをDBにINSERT、ファイルにWRITE、メッセージキューにPUSHなど） 

`ItemWriter` はデータを1件ずつ書き込みません。Chunk単位でまとめて一度にデータを書き込みます。`write()` メソッドのパラメータ型が `Chunk` であることに注目しましょう。 と がアイテムを1つずつ返し、入力として受け取るのとは異なり、 はデータ塊（Chunk）を一度に入力として受け取り、一度に書き込みます。
