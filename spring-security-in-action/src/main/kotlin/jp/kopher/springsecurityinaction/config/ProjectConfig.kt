package jp.kopher.springsecurityinaction.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class ProjectConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.formLogin{
            it.defaultSuccessUrl("/main", true)
        }
        http.authorizeHttpRequests{
            it.anyRequest().authenticated()
        }
        return http.build()
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun sCryptPasswordEncoder(): SCryptPasswordEncoder {
        return SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8()
    }
}