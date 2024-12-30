package jp.co.kopher.coursetracker.service.impl

import jp.co.kopher.coursetracker.service.LoginAttemptService
import jp.co.kopher.coursetracker.service.UserService
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    val userService: UserService,
    val loginAttemptService: LoginAttemptService,
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {

        if (username != null && loginAttemptService.isBlocked(username)) {
            throw LockedException("User $username is blocked")
        }

        val applicationUser = userService.findByUsername(username)
            ?: throw UsernameNotFoundException(username)

        return User.withUsername(applicationUser.username)
            .password(applicationUser.password)
            .roles("USER")
            .disabled(false)
            .build()
    }
}