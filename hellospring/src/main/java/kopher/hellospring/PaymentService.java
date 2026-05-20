package kopher.hellospring;

public class PaymentService {
    public Payment prepare() {
        return new Payment();
    }

    public static void main(String[] args) {
        PaymentService service = new PaymentService();
        Payment payment = service.prepare();
        System.out.println(payment);
    }
}
