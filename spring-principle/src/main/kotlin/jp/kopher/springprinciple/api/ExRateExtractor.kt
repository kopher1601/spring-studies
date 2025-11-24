package jp.kopher.springprinciple.api

import java.math.BigDecimal

interface ExRateExtractor {
    fun extract(response: String): BigDecimal
}