package jp.co.kopher.coursetracker.service

import jp.co.kopher.coursetracker.model.EmailVerification
import jp.co.kopher.coursetracker.repository.EmailVerificationRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class EmailVerificationService(
    val repository: EmailVerificationRepository
) {

    fun generateVerification(username: String): String {
        if (!repository.existsByUsername(username)) {
            val emailVerification = EmailVerification(username)
            val verification = repository.save(emailVerification)
            return verification.verificationId
        }
        return getVerificationIdByUsername(username)
    }

    fun getVerificationIdByUsername(username: String): String {
        val verification = repository.findByUsername(username) ?: return ""
        return verification.verificationId
    }

    fun getUsernameForVerificationId(verificationId: String): String {
        val verification = repository.findByIdOrNull(verificationId) ?: return ""
        return verification.username
    }
}