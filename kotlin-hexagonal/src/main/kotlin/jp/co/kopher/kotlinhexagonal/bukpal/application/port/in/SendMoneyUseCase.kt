package jp.co.kopher.kotlinhexagonal.bukpal.application.port.`in`

import org.springframework.validation.annotation.Validated

interface SendMoneyUseCase {
    fun sendMoney(@Validated command: SendMoneyCommand): Boolean
}