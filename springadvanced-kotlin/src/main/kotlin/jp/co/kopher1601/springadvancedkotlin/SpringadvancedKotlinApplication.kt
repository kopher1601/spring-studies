package jp.co.kopher1601.springadvancedkotlin

import jp.co.kopher1601.springadvancedkotlin.config.AppV1Config
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@Import(AppV1Config::class)
@SpringBootApplication(scanBasePackages = ["jp.co.kopher1601.springadvancedkotlin.app"])
class SpringadvancedKotlinApplication

fun main(args: Array<String>) {
    runApplication<SpringadvancedKotlinApplication>(*args)
}
