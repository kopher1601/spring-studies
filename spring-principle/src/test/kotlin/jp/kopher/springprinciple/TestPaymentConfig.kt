package jp.kopher.springprinciple

import jp.kopher.springprinciple.payment.ExRateProvider
import jp.kopher.springprinciple.payment.ExRateProviderStub
import jp.kopher.springprinciple.payment.PaymentService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

@Configuration
class TestPaymentConfig {
    @Bean
    fun paymentService(): PaymentService {
        return PaymentService(exRateProvider(), clock())
    }

    @Bean
    fun exRateProvider(): ExRateProvider {
        return ExRateProviderStub(1_000.toBigDecimal())
    }

    @Bean
    fun clock(): Clock {
        return Clock.fixed(Instant.now(), ZoneId.systemDefault())
    }
}