package jp.kopher1601.springsecurity.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController {

    @GetMapping("/welcome")
    fun sayWelcome(): String {
        return "Welcome to Kopher1601's Spring Security Demo."
    }

}