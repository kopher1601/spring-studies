package kopher.hellospring;

import java.io.IOException;
import java.math.BigDecimal;

public interface ExRateProvider {
    BigDecimal getWebExRate(String currency) throws IOException;
}
