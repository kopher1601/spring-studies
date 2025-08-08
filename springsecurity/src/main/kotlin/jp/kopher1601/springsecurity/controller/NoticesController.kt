package jp.kopher1601.springsecurity.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NoticesController {

    @GetMapping("/notices")
    fun geNotices(): String {
        return "Here are the notices from the DB"
    }

}