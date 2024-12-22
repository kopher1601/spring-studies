package jp.kopher.customfailureanalyzer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CustomFailureAnalyzerApplication

fun main(args: Array<String>) {
    runApplication<CustomFailureAnalyzerApplication>(*args)
}
