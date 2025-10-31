package jp.kopher.springsecurityinaction.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloController {

    @GetMapping("/home")
    fun home(): String {
        return "home.html"
    }
}