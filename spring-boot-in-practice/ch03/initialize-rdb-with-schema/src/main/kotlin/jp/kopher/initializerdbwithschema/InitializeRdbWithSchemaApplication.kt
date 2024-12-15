package jp.kopher.initializerdbwithschema

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InitializeRdbWithSchemaApplication

fun main(args: Array<String>) {
    runApplication<InitializeRdbWithSchemaApplication>(*args)
}
