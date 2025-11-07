package jp.kopher.springsecurityinaction.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello(): String {
        return "Get Hello!"
    }

    @PostMapping("/hello")
    fun helloPost(): String {
        return "Post Hello!"
    }
}