package jp.co.kopher1601.stockexample.repository

import jp.co.kopher1601.stockexample.domain.Stock
import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository: JpaRepository<Stock, Long> {
}