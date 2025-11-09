package jp.kopher.springsecurityinaction.services

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class NameService {

    @PreAuthorize("hasAuthority('write')")
    fun getName(): String {
        return "Fantastico"
    }

}