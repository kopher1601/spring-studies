package jp.co.kopher.kotlinhexagonal.bukpal.application.service

import jp.co.kopher.kotlinhexagonal.bukpal.application.port.`in`.SendMoneyCommand
import jp.co.kopher.kotlinhexagonal.bukpal.application.port.`in`.SendMoneyUseCase
import org.springframework.validation.annotation.Validated


class SendMoneyService(
    private val loadAccountPort: LoadAccountPort,
    private val accountLock: AccountLock,
    private val updateAccountStatePort: UpdateAccountStatePort,
): SendMoneyUseCase {
    override fun sendMoney(@Validated command: SendMoneyCommand): Boolean {
        TODO("Not yet implemented")
    }
}