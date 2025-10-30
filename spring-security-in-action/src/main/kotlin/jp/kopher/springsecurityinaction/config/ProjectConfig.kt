package jp.kopher.springsecurityinaction.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class ProjectConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.httpBasic{
            it.realmName("OTHER")
            // 이름에 혼동의 여지가 있지만 401 에러가 났을 경우 불리는 곳이다.
            it.authenticationEntryPoint(CustomEntryPoint())
        }
        http.authorizeHttpRequests{
            it.anyRequest().authenticated()
        }
        return http.build()
    }
}