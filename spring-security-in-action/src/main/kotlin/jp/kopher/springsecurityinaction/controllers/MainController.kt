package jp.kopher.springsecurityinaction.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MainController {

    private val log = org.slf4j.LoggerFactory.getLogger(this::class.java)

    @GetMapping("/")
    fun main(): String {
        return "main"
    }

    @PostMapping("/test")
    @ResponseBody
    fun test(): String {
        log.info("Test method called")
        return "HELLO"
    }
}