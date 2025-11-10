package jp.kopher.springsecurityinaction.controllers

import jp.kopher.springsecurityinaction.services.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(
    private val bookService: BookService,
) {

    @GetMapping("/book/details/{name}")
    fun getDetails(@PathVariable name: String) = bookService.getBookDetails(name)
}