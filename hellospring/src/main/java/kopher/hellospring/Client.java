package kopher.hellospring;

import kopher.hellospring.payment.Payment;
import kopher.hellospring.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService service = beanFactory.getBean(PaymentService.class);

        Payment payment = service.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);

        Payment payment2 = service.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment2);

        TimeUnit.SECONDS.sleep(3);

        Payment payment3 = service.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment3);
    }
}
