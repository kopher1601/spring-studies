package jp.kopher.springsecurityinaction.security

import jp.kopher.springsecurityinaction.model.Document
import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
class DocumentPermissionEvaluator: PermissionEvaluator {
    override fun hasPermission(authentication: Authentication?, target: Any?, permission: Any?): Boolean {
        val document = target as Document
        val p = permission as String

        val admin = authentication?.authorities?.any { it.authority == p }

        return admin == true || document.owner == authentication?.name
    }

    override fun hasPermission(
        authentication: Authentication?,
        targetId: Serializable?,
        targetType: String?,
        permission: Any?
    ): Boolean {
        return false
    }
}