package jp.kopher.springsecurityinaction.config

import jp.kopher.springsecurityinaction.model.User
import jp.kopher.springsecurityinaction.service.InMemoryUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class ProjectConfig {

    @Bean
    fun userDetailsService(): InMemoryUserDetailsService {
        User("john", "1234", "read").let {
            return InMemoryUserDetailsService(listOf(it))
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }
}