package jp.kopher.springsecurityinaction.services

import jp.kopher.springsecurityinaction.model.Document
import jp.kopher.springsecurityinaction.repositories.DocumentRepository
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.stereotype.Service

@Service
class DocumentService(
    private val documentRepository: DocumentRepository,
) {

    @PostAuthorize("hasPermission(returnObject, 'ROLE_admin')")
    fun getDocument(code: String): Document {
        return documentRepository.findDocument(code)
            ?: throw IllegalArgumentException("Document not found")
    }
}