package jp.co.kopher1601.stockexample.service

import jp.co.kopher1601.stockexample.repository.StockRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class StockService(
    private val stockRepository: StockRepository,
) {

    fun decrease(id: Long, quantity: Long) {
        // Stock 조회
        val stock = stockRepository.findByIdOrNull(id)
            ?: throw IllegalStateException("Stock not found: $id")

        // 재고 감소
        stock.decrease(quantity)

        // 갱신된 값을 저장
        stockRepository.saveAndFlush(stock)
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun decreaseNamedLock(id: Long, quantity: Long) {
        // Stock 조회
        val stock = stockRepository.findByIdOrNull(id)
            ?: throw IllegalStateException("Stock not found: $id")

        // 재고 감소
        stock.decrease(quantity)

        // 갱신된 값을 저장
        stockRepository.saveAndFlush(stock)
    }
}