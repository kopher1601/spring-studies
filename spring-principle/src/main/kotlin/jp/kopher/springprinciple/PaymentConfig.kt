package jp.kopher.springprinciple

import jp.kopher.springprinciple.exrate.WebApiExRateProvider
import jp.kopher.springprinciple.payment.ExRateProvider
import jp.kopher.springprinciple.payment.PaymentService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import java.time.Clock

@Configuration
@ComponentScan
class PaymentConfig {
    @Bean
    fun paymentService(): PaymentService {
        return PaymentService(exRateProvider(), clock())
    }

    @Bean
    fun exRateProvider(): ExRateProvider {
        return WebApiExRateProvider()
    }

    @Bean
    fun clock(): Clock {
        return Clock.systemDefaultZone()
    }

}