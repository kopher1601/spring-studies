package jp.kopher1601.springsecurity.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoansController {

    @GetMapping("/myLoans")
    fun getLoansDetails(): String {
        return "Here are the loan details from the DB"
    }

}