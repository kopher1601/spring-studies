package jp.co.kopher1601.stockexample.service

import jp.co.kopher1601.stockexample.repository.StockRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OptimisticLockStockService(
    private val stockRepository: StockRepository,
) {

    @Transactional
    fun decrease(id: Long, quantity: Long) {
        val stock = stockRepository.findByIdWithOptimisticLock(id) ?: throw NotFoundException()

        stock.decrease(quantity)
        stockRepository.save(stock)
    }
}