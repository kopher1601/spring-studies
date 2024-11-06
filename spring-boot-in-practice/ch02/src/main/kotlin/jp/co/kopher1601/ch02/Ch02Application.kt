package jp.co.kopher1601.ch02

import jp.co.kopher1601.ch02.config.DbConfig
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Ch02Application

fun main(args: Array<String>) {
    val log = LoggerFactory.getLogger("Application")
    val runApplication = runApplication<Ch02Application>(*args)
    val dbConfig = runApplication.getBean(DbConfig::class.java)
    log.info(dbConfig.toString())
}


