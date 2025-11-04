package jp.kopher.springsecurityinaction.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @PostMapping("/a")
    fun postEndpointA(): String {
        return "POST Endpoint A"
    }

    @GetMapping("/a")
    fun getEndpointA(): String {
        return "GET Endpoint A"
    }

    @GetMapping("/a/b")
    fun getEndpointB(): String {
        return "GET Endpoint B"
    }

    @GetMapping("/a/b/c")
    fun getEndpointC(): String {
        return "GET Endpoint C"
    }
}