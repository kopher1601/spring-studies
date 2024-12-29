package jp.kopher.customloginpage.security

import jp.kopher.customloginpage.repository.ApplicationUserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class CustomUserDetailsService(
    private val repository: ApplicationUserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val foundUser = repository.findByUsername(username) ?: throw UsernameNotFoundException(username)

        return User.builder()
            .username(foundUser.username)
            .password(foundUser.password)
            .disabled(foundUser.verified)
            .accountExpired(foundUser.accountCredentialsExpired)
            .accountLocked(foundUser.locked)
            .roles("USER")
            .build()
    }
}