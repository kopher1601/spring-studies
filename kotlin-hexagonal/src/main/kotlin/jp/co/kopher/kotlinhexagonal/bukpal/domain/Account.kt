package jp.co.kopher.kotlinhexagonal.bukpal.domain

import java.time.LocalDateTime

class Account(
    private val id: AccountId,
    private val baselineBalance: Money,
    private val activityWindow: ActivityWindow,

) {

    fun calculateBalance(): Money {
        return Money.add(baselineBalance, activityWindow.calculateBalance(id))
    }

    fun withdraw(money: Money, targetAccountId: AccountId): Boolean {
        if (!mayWithdraw(money)) {
            return false
        }

        val withdrawal = Activity(
            ownerAccountId = id,
            _sourceAccountId = id,
            _targetAccountId = targetAccountId,
            _timestamp = LocalDateTime.now(),
            _money = money
        )
        activityWindow.addActivity(withdrawal)
        return true
    }

    private fun mayWithdraw(money: Money): Boolean {
        return Money.add(
            calculateBalance(),
            money.negate()
        ).isPositive()
    }

    fun deposit(money: Money, sourceAccountId: AccountId): Boolean {
        val deposit = Activity(
            ownerAccountId = id,
            _sourceAccountId = sourceAccountId,
            _targetAccountId = id,
            _timestamp = LocalDateTime.now(),
            _money = money
        )
        activityWindow.addActivity(deposit)
        return true
    }

    class AccountId(
        private val value: Long,
    )
}