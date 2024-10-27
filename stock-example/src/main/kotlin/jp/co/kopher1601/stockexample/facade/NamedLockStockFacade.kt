package jp.co.kopher1601.stockexample.facade

import jp.co.kopher1601.stockexample.repository.LockRepository
import jp.co.kopher1601.stockexample.service.StockService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class NamedLockStockFacade(
    private val lockRepository: LockRepository,
    private val stockService: StockService,
) {

    @Transactional
    fun decrease(id: Long, quantity: Long) {
        try {
            lockRepository.getLock(id.toString())
            stockService.decreaseNamedLock(id, quantity)
        } finally {
            lockRepository.releaseLock(id.toString())
        }
    }
}