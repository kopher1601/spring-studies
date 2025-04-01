package jp.co.kopher.kotlinhexagonal.bukpal.domain

import java.math.BigInteger

data class Money(
    private val amount: BigInteger,
) {

    fun isPositiveOrZero(): Boolean {
        return amount >= BigInteger.ZERO
    }

    fun isNegative(): Boolean {
        return amount < BigInteger.ZERO
    }

    fun isPositive(): Boolean {
        return amount > BigInteger.ZERO
    }

    fun isGreaterThanOrEqualTo(money: Money): Boolean {
        return amount >= money.amount
    }

    fun isGreaterThan(money: Money): Boolean {
        return amount.compareTo(money.amount) >= 1
    }

    fun minus(money: Money): Money {
        return Money(amount.subtract(money.amount))
    }

    fun plus(money: Money): Money {
        return Money(amount.add(money.amount))
    }

    fun negate(): Money {
        return Money(amount.negate())
    }

    companion object {
        val ZERO: Money = of(0L)

        fun of(value: Long): Money {
            return Money(value.toBigInteger())
        }

        fun add(a: Money, b: Money): Money {
            return Money(a.amount.add(b.amount))
        }

        fun subtract(a: Money, b: Money): Money {
            return Money(a.amount.subtract(b.amount))
        }
    }
}

