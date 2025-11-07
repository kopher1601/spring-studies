package jp.kopher.springsecurityinaction.config

import jp.kopher.springsecurityinaction.filters.CsrfTokenLogger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.csrf.CsrfFilter

@Configuration
class ProjectConfig(
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.addFilterAfter(CsrfTokenLogger(), CsrfFilter::class.java)
            .authorizeHttpRequests {
                it.anyRequest().permitAll()
            }
        return http.build()
    }
}