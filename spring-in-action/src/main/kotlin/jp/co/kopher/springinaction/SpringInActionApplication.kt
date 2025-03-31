package jp.co.kopher.springinaction

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringInActionApplication

fun main(args: Array<String>) {
    runApplication<SpringInActionApplication>(*args)
}
