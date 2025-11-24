package jp.kopher.springprinciple

import jp.kopher.springprinciple.api.ApiTemplate
import jp.kopher.springprinciple.api.ErApiExRateExtractor
import jp.kopher.springprinciple.api.HttpClientApiExecutor
import jp.kopher.springprinciple.exrate.RestTemplateExRateProvider
import jp.kopher.springprinciple.exrate.WebApiExRateProvider
import jp.kopher.springprinciple.payment.ExRateProvider
import jp.kopher.springprinciple.payment.PaymentService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.time.Clock

@Configuration
@ComponentScan
class PaymentConfig {
    @Bean
    fun paymentService(): PaymentService {
        return PaymentService(exRateProvider(), clock())
    }

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    @Bean
    fun exRateProvider(): ExRateProvider {
        return RestTemplateExRateProvider(restTemplate())
    }

    @Bean
    fun apiTemplate(): ApiTemplate {
        return ApiTemplate(HttpClientApiExecutor(), ErApiExRateExtractor())
    }

    @Bean
    fun clock(): Clock {
        return Clock.systemDefaultZone()
    }

}