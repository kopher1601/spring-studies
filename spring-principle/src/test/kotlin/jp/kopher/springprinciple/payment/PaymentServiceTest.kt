package jp.kopher.springprinciple.payment

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class PaymentServiceTest {

    private lateinit var clock: Clock

    @BeforeEach
    fun beforeEach() {
        clock = Clock.fixed(Instant.now(), ZoneId.systemDefault())
    }

    @Test
    fun `요구사항 3가지를 잘 충족했는지 검증`(){

        testAmount(exRate = 500.toBigDecimal(), convertedAmount = 5_000.toBigDecimal(), clock = clock)
    }

    @Test
    fun validUntil() {
        val paymentService = PaymentService(ExRateProviderStub(1_000.toBigDecimal()), clock)
        val payment = paymentService.prepare(1L, "USD", BigDecimal.TEN)

        val now = LocalDateTime.now(clock)
        val expectedValidUntil = now.plusMinutes(30)

        assertThat(payment.validUntil).isAfter(expectedValidUntil)
    }

    private fun testAmount(exRate: BigDecimal, convertedAmount: BigDecimal, clock: Clock) {
        val paymentService = PaymentService(ExRateProviderStub(exRate), clock)

        val payment = paymentService.prepare(1L, "USD", BigDecimal.TEN)

        // BigDecimal 는 isEqualByComparingTo 를 사용해야 한다.
        assertThat(payment.exRate).isEqualByComparingTo(exRate)
        assertThat(payment.convertedAmount).isEqualByComparingTo(convertedAmount)
    }

}