package kopher.hellospring.payment;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentTest {
    @Test
    void createPrepared() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        Payment payment = Payment.createPrepared(1L,
                "USD",
                BigDecimal.TEN,
                BigDecimal.valueOf(1_000),
                LocalDateTime.now(clock));

        assertThat(payment.getConvertedAmount()).isEqualTo(BigDecimal.valueOf(1_0000));
        assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
    }

    @Test
    void isValid() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        Payment payment = Payment.createPrepared(1L,
                "USD",
                BigDecimal.TEN,
                BigDecimal.valueOf(1_000),
                LocalDateTime.now(clock));

        assertThat(payment.isValid(clock)).isTrue();
        assertThat(
                payment.isValid(Clock.offset(clock, Duration.of(30, ChronoUnit.MINUTES)))).isFalse();
    }

}
