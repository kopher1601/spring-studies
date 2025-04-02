package jp.co.kopher.kotlinhexagonal.bukpal.domain

import java.time.LocalDateTime

class ActivityWindow(
    private val activities: MutableList<Activity>,
) {

    val startTimestamp: LocalDateTime
        get() = activities.minByOrNull { it.timestamp }?.timestamp
            ?: throw IllegalStateException()

    val endTimestamp: LocalDateTime
        get() = activities.maxByOrNull { it.timestamp }?.timestamp
            ?: throw IllegalStateException()

    fun calculateBalance(accountId: Account.AccountId): Money {
        val depositBalance = activities
            .filter { it.targetAccountId == accountId }
            .map { it.money }
            .fold(Money.ZERO, Money::add)

        val withdrawalBalance = activities
            .filter { it.sourceAccountId == accountId }
            .map { it.money }
            .fold(Money.ZERO, Money::add)

        return Money.add(depositBalance, withdrawalBalance.negate())
    }

    fun getActivities(): List<Activity> = activities.toList()

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    companion object {
        fun of(vararg activities: Activity) = ActivityWindow(activities.toMutableList())
    }
}