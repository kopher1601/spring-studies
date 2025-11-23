package jp.kopher.springprinciple.payment

import jp.kopher.springprinciple.TestObjectFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [TestObjectFactory::class])
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class PaymentServiceSpringTest(
    private val paymentService: PaymentService,
) {

    @Test
    fun `요구사항 3가지를 잘 충족했는지 검증`(){
        paymentService.run {
            val payment = prepare(1L, "USD", BigDecimal.TEN)
            assertThat(payment.exRate).isEqualByComparingTo(1_000.toBigDecimal())
            assertThat(payment.convertedAmount).isEqualByComparingTo(10_000.toBigDecimal())
            assertThat(payment.validUntil).isAfter(
                LocalDateTime.now()
            )
        }
    }

}