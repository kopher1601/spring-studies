package jp.co.kopher1601.configurationproperties

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
//@EnableConfigurationProperties(AppProperties::class)
@ConfigurationPropertiesScan
class ConfigurationPropertiesApplication

fun main(args: Array<String>) {
    runApplication<ConfigurationPropertiesApplication>(*args)
}
