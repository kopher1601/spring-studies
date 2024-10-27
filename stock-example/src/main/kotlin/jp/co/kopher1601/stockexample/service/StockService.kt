package jp.co.kopher1601.stockexample.service

import jp.co.kopher1601.stockexample.repository.StockRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class StockService(
    private val stockRepository: StockRepository,
) {

    fun decrease(id: Long, quantity: Long) {
        // synchronized 를 이용한 해결
        synchronized(this) {
            // Stock 조회
            val stock = stockRepository.findByIdOrNull(id)
                ?: throw IllegalStateException("Stock not found: $id")

            // 재고 감소
            stock.decrease(quantity)

            // 갱신된 값을 저장
            stockRepository.saveAndFlush(stock)
        }

    }
}