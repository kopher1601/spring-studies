package jp.kopher.springbootinpractice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootInPracticeApplication

fun main(args: Array<String>) {
    runApplication<SpringBootInPracticeApplication>(*args) {
        setDefaultProperties(
            mapOf(
                "spring.config.on-not-found" to "ignore"
            )
        )
    }
}

