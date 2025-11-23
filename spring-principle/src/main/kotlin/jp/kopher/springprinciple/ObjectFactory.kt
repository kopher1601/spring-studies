package jp.kopher.springprinciple

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