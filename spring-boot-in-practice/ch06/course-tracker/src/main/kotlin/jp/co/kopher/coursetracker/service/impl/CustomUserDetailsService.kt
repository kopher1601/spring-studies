package jp.co.kopher.coursetracker.service.impl

import jp.co.kopher.coursetracker.service.UserService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    val userService: UserService,
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {

        val applicationUser = userService.findByUsername(username)
            ?: throw UsernameNotFoundException(username)

        return User.withUsername(applicationUser.username)
            .password(applicationUser.password)
            .roles("USER")
            .disabled(false)
            .build()
    }
}