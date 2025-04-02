package jp.co.kopher.kotlinhexagonal.bukpal.domain

import java.time.LocalDateTime

class Activity(
    private val id: ActivityId? = null,
    private val ownerAccountId: Account.AccountId,
    private val _sourceAccountId: Account.AccountId,
    private val _targetAccountId: Account.AccountId,
    private val _timestamp: LocalDateTime,
    private val _money: Money,
) {

    val timestamp: LocalDateTime
        get() = _timestamp

    val targetAccountId: Account.AccountId
        get() = _targetAccountId

    val sourceAccountId: Account.AccountId
        get() = _sourceAccountId

    val money: Money
        get() = _money

    class ActivityId(
        private val value: Long,
    )
}