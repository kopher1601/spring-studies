package jp.kopher.springsecurityinaction.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class InMemoryUserDetailsService(
    private val userDetails: List<UserDetails>,
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return userDetails.firstOrNull { it.username == username }
            ?: throw UsernameNotFoundException("User not found")
    }
}