package kopher.hellospring;

import kopher.hellospring.exrate.CachedExRateProvider;
import kopher.hellospring.exrate.WebApiExRateProvider;
import kopher.hellospring.payment.ExRateProvider;
import kopher.hellospring.payment.ExRateProviderStub;
import kopher.hellospring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@ComponentScan
public class TestObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }

}
