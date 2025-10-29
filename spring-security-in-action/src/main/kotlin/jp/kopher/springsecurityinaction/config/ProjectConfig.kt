package jp.kopher.springsecurityinaction.config

import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import javax.sql.DataSource


@EnableAsync
@Configuration
class ProjectConfig {

    @Bean
    fun userDetailsService(dataSource: DataSource): UserDetailsService {
        return JdbcUserDetailsManager(dataSource)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }

    @Bean
    fun initializingBean(): InitializingBean {
        return InitializingBean {
            SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL)
        }
    }
}