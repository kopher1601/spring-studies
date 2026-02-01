package jp.kopher1601.springcloudnativeinaction

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringCloudNativeInActionApplication

fun main(args: Array<String>) {
    runApplication<SpringCloudNativeInActionApplication>(*args)
}
