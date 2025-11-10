package jp.kopher.springsecurityinaction.controllers

import jp.kopher.springsecurityinaction.services.DocumentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class DocumentController(
    private val documentService: DocumentService,
) {

    @GetMapping("/documents/{code}")
    fun getDetails(@PathVariable code: String) = documentService.getDocument(
        code = code
    )
}