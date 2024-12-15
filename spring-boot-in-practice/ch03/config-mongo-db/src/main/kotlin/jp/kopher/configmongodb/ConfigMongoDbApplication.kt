package jp.kopher.configmongodb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConfigMongoDbApplication

fun main(args: Array<String>) {
    runApplication<ConfigMongoDbApplication>(*args)
}
