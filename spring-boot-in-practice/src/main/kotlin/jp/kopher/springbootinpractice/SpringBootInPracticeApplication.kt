package jp.kopher.springbootinpractice

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringBootInPracticeApplication {
    private val log = org.slf4j.LoggerFactory.getLogger(SpringBootInPracticeApplication::class.java)

    @Bean
    fun commandLineRunner(): CommandLineRunner {
        return CommandLineRunner {
            log.info("CommandLineRunner")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<SpringBootInPracticeApplication>(*args)
}

