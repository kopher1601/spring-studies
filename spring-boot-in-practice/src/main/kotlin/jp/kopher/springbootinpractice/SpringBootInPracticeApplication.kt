package jp.kopher.springbootinpractice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootInPracticeApplication

private val log = org.slf4j.LoggerFactory.getLogger(SpringBootInPracticeApplication::class.java)

fun main(args: Array<String>) {

    val application = runApplication<SpringBootInPracticeApplication>(*args)
    application.getBean(DbConfiguration::class.java).let {
        log.info(it.toString())
    }
}

