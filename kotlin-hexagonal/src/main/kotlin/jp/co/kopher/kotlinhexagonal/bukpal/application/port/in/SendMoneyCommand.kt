package jp.co.kopher.kotlinhexagonal.bukpal.application.port.`in`

import jp.co.kopher.kotlinhexagonal.bukpal.domain.Account.AccountId
import jp.co.kopher.kotlinhexagonal.bukpal.domain.Money
import org.jetbrains.annotations.NotNull

data class SendMoneyCommand(
    @field:NotNull
    private val sourceAccountId: AccountId,
    @field:NotNull
    private val targetAccountId: AccountId,
    @field:NotNull
    private val money: Money,
)