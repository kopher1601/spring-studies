package jp.kopher.springsecurityinaction.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    @GetMapping("/product/{code}")
    fun product(@PathVariable code: String): String {
        return "Product Code: $code"
    }
}