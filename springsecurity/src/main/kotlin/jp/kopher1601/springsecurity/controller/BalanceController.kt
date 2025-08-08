package jp.kopher1601.springsecurity.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BalanceController {

    @GetMapping("/myBalance")
    fun getBalanceDetails(): String {
        return "Here are the balance details from the DB"
    }

}