package jp.kopher.springprinciple.payment

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class PaymentServiceTest {

    @Test
    fun `요구사항 3가지를 잘 충족했는지 검증`(){
        testAmount(exRate = 500.toBigDecimal(), convertedAmount = 5_000.toBigDecimal())

        // 원화환산금액의 유효시간 계산
//        assertThat(payment.validUntil).isAfter(LocalDateTime.now())
    }

    private fun testAmount(exRate: BigDecimal, convertedAmount: BigDecimal) {
        val paymentService = PaymentService(ExRateProviderStub(exRate))

        val payment = paymentService.prepare(1L, "USD", BigDecimal.TEN)

        // BigDecimal 는 isEqualByComparingTo 를 사용해야 한다.
        assertThat(payment.exRate).isEqualByComparingTo(exRate)
        assertThat(payment.convertedAmount).isEqualByComparingTo(convertedAmount)
    }

}