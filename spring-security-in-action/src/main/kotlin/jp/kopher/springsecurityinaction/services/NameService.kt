package jp.kopher.springsecurityinaction.services

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class NameService {

    private val secretNames = mapOf(
        "natalie" to listOf("Energico", "Perfecto"),
        "emma" to listOf("Fantastico")
    )


    @PreAuthorize("hasAuthority('write')")
    fun getName(): String {
        return "Fantastico"
    }

    @PreAuthorize("#name == authentication.principal.username")
    fun getSecretNames(name: String): List<String> {
        return secretNames[name] ?: emptyList()
    }

}