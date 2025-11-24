package jp.kopher.springprinciple.payment

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.Clock
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

class PaymentTest {

    @Test
    fun createPrepared() {
        val clock = Clock.fixed(Instant.now(), ZoneId.systemDefault())

        val payment = Payment.createPrepared(
            orderId = 1L,
            currency = "USD",
            foreignCurrencyAmount = BigDecimal.TEN,
            exRate = 1_000.toBigDecimal(),
            now = LocalDateTime.now(clock),
        )

        assertThat(payment.convertedAmount).isEqualByComparingTo(10_000.toBigDecimal())
        assertThat(payment.validUntil).isEqualTo(LocalDateTime.now(clock).plusMinutes(30))
    }

    @Test
    fun isValid() {
        val clock = Clock.fixed(Instant.now(), ZoneId.systemDefault())

        val payment = Payment.createPrepared(
            orderId = 1L,
            currency = "USD",
            foreignCurrencyAmount = BigDecimal.TEN,
            exRate = 1_000.toBigDecimal(),
            now = LocalDateTime.now(clock),
        )

        assertThat(payment.isValid(clock)).isTrue
        assertThat(
            payment.isValid(Clock.offset(clock, Duration.of(30, ChronoUnit.MINUTES)))).isFalse
    }
}