package jp.kopher.springsecurityinaction.services

import jp.kopher.springsecurityinaction.model.Employee
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.stereotype.Service

@Service
class BookService {

    private val records = mapOf(
        "emma" to Employee("Emma Thompson", listOf("Karamazov Brothers"), listOf("accountant", "reader")),
        "natalie" to Employee("Natalie Parker", listOf("Beautiful Paris"), listOf("researcher")),
    )

    @PostAuthorize("returnObject.roles.contains('reader')")
    fun getBookDetails(name: String): Employee {
        return records[name] ?: throw IllegalArgumentException("Invalid user name")
    }
}