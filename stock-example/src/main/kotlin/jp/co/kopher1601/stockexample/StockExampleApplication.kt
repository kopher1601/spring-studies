package jp.co.kopher1601.stockexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StockExampleApplication

fun main(args: Array<String>) {
    runApplication<StockExampleApplication>(*args)
}
