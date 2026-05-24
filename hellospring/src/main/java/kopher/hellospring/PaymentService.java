package kopher.hellospring;

import tools.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PaymentService {
    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
        BigDecimal exRate = getExRate(currency);

        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);

        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    private BigDecimal getExRate(String currency) throws IOException {
        URL url = URI.create("https://open.er-api.com/v6/latest" + currency).toURL();
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String response = br.lines().collect(Collectors.joining());
        br.close();

        System.out.println("response = " + response);
        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);
        return data.rates().get("KRW");
    }

    public static void main(String[] args) throws IOException {
        PaymentService service = new PaymentService();
        Payment payment = service.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
