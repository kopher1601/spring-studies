package jp.kopher1601.springsecurity.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ContactController {

    @GetMapping("/contact")
    fun getContactDetails(): String {
        return "Here are the contact details from the DB"
    }

}