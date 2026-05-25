package kopher.hellospring;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) throws IOException {
        ObjectFactory factory = new ObjectFactory();
        PaymentService service = factory.paymentService();
        Payment payment = service.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
