package jp.kopher.springbatchwithkotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import kotlin.system.exitProcess

@SpringBootApplication
class SpringBatchWithKotlinApplication

fun main(args: Array<String>) {
    exitProcess(SpringApplication.exit(SpringApplication.run(SpringBatchWithKotlinApplication::class.java, *args)))
}
