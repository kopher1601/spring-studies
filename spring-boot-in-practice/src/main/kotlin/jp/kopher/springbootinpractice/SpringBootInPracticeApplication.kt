package jp.kopher.springbootinpractice

import jp.kopher.springbootinpractice.properties.AppProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AppProperties::class)
class SpringBootInPracticeApplication

private val log = org.slf4j.LoggerFactory.getLogger(SpringBootInPracticeApplication::class.java)

fun main(args: Array<String>) {

    val application = runApplication<SpringBootInPracticeApplication>(*args)
}

