package jp.kopher.springbootinpractice

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment

@Configuration
@PropertySource("classpath:dbConfig.properties")
class DbConfiguration(
    private val env: Environment,
) {
    override fun toString(): String {
        return "User: ${env.getProperty("db.user")}, Password: ${env.getProperty("db.password")}"
    }
}