package board.common.snowflake

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.util.concurrent.*

internal class SnowflakeTest {
    var snowflake: Snowflake = Snowflake()

    @Test
    @Throws(ExecutionException::class, InterruptedException::class)
    fun nextIdTest() {
        // given
        val executorService = Executors.newFixedThreadPool(10)
        val futures: MutableList<Future<List<Long>>> = ArrayList()
        val repeatCount = 1000
        val idCount = 1000

        // when
        for (i in 0..<repeatCount) {
            futures.add(executorService.submit<List<Long>> {
                generateIdList(
                    snowflake,
                    idCount
                )
            })
        }

        // then
        val result: MutableList<Long> = ArrayList()
        for (future in futures) {
            val idList = future.get()
            for (i in 1..<idList.size) {
                Assertions.assertThat(idList[i]).isGreaterThan(idList[i - 1])
            }
            result.addAll(idList)
        }
        Assertions.assertThat(result.stream().distinct().count()).isEqualTo((repeatCount * idCount).toLong())

        executorService.shutdown()
    }

    fun generateIdList(snowflake: Snowflake, count: Int): List<Long> {
        var count = count
        val idList: MutableList<Long> = ArrayList()
        while (count-- > 0) {
            idList.add(snowflake.nextId())
        }
        return idList
    }

    @Test
    @Throws(InterruptedException::class)
    fun nextIdPerformanceTest() {
        // given
        val executorService = Executors.newFixedThreadPool(10)
        val repeatCount = 1000
        val idCount = 1000
        val latch = CountDownLatch(repeatCount)

        // when
        val start = System.nanoTime()
        for (i in 0..<repeatCount) {
            executorService.submit {
                generateIdList(snowflake, idCount)
                latch.countDown()
            }
        }

        latch.await()

        val end = System.nanoTime()
        println("times = %s ms".formatted((end - start) / 1000000))

        executorService.shutdown()
    }
}