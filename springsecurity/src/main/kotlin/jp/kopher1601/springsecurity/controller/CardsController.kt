package jp.kopher1601.springsecurity.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CardsController {

    @GetMapping("/myCards")
    fun getCardsDetails(): String {
        return "Here are the card details from the DB"
    }

}