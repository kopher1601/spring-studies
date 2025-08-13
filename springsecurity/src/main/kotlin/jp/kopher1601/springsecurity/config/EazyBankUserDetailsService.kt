package jp.kopher1601.springsecurity.config

import jp.kopher1601.springsecurity.repository.CustomerRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class EazyBankUserDetailsService(
    private val customerRepository: CustomerRepository,
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val customer = (customerRepository.findByEmail(username!!)
            ?: throw UsernameNotFoundException("The username $username was not found in the database"))

        val authorities = listOf(SimpleGrantedAuthority(customer.role))

        return User(
            customer.email,
            customer.pwd,
            authorities,
        )
    }
}