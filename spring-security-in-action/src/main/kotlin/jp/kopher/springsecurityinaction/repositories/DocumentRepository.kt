package jp.kopher.springsecurityinaction.repositories

import jp.kopher.springsecurityinaction.model.Document
import org.springframework.stereotype.Repository

@Repository
class DocumentRepository {

    private val documents = mapOf(
        "abc123" to Document("natalie"),
        "qwe123" to Document("natalie"),
        "asd555" to Document("emma"),
    )

    fun findDocument(code: String): Document? {
        return documents[code]
    }
}