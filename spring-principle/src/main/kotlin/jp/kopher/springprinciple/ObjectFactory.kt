package jp.kopher.springprinciple

import jp.kopher.springprinciple.exrate.CachedExRateProvider
import jp.kopher.springprinciple.payment.ExRateProvider
import jp.kopher.springprinciple.exrate.WebApiExRateProvider
import jp.kopher.springprinciple.payment.PaymentService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan
class ObjectFactory {
    @Bean
    fun paymentService(): PaymentService {
        return PaymentService(cachedExRateProvider())
    }

    @Bean
    fun cachedExRateProvider(): ExRateProvider {
        return CachedExRateProvider(exRateProvider())
    }

    @Bean
    fun exRateProvider(): ExRateProvider {
        return WebApiExRateProvider()
    }
}