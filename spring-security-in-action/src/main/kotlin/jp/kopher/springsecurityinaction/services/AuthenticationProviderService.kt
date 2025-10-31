package jp.kopher.springsecurityinaction.services

import jp.kopher.springsecurityinaction.entities.enums.EncryptionAlgorithm.BCRYPT
import jp.kopher.springsecurityinaction.entities.enums.EncryptionAlgorithm.SCRYPT
import jp.kopher.springsecurityinaction.model.CustomUserDetails
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationProviderService(
    private val userDetailsService: JpaUserDetailsService,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val sCryptPasswordEncoder: SCryptPasswordEncoder,
): AuthenticationProvider {

    private val log = org.slf4j.LoggerFactory.getLogger(this::class.java)

    override fun authenticate(authentication: Authentication?): Authentication {

        log.info("AuthenticationProviderService.authenticate")

        val username = authentication?.name
        val password = authentication?.credentials.toString()

        val user = userDetailsService.loadUserByUsername(username) as CustomUserDetails

        return when (user.user.algorithm) {
            BCRYPT -> checkPassword(user, password, bCryptPasswordEncoder)
            SCRYPT -> checkPassword(user, password, sCryptPasswordEncoder)
        }
    }

    private fun checkPassword(
        user: CustomUserDetails,
        rawPassword: String,
        encoder: PasswordEncoder
    ): Authentication {
        if (encoder.matches(rawPassword, user.password)) {
            return UsernamePasswordAuthenticationToken(user.username, user.password, user.authorities)
        } else {
            throw BadCredentialsException("Bad credentials")
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}