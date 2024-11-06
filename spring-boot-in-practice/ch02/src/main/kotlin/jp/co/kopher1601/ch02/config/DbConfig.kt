package jp.co.kopher1601.ch02.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment

@Configuration
@PropertySource("classpath:dbConfig.properties")
class DbConfig(
    private val env: Environment,
) {

    override fun toString(): String {
        return "Username: ${env.getProperty("user")} Password: ${env.getProperty("password")}"
    }
}