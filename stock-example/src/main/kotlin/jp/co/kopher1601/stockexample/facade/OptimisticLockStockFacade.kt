package jp.co.kopher1601.stockexample.facade

import jp.co.kopher1601.stockexample.service.OptimisticLockStockService
import org.springframework.stereotype.Component

@Component
class OptimisticLockStockFacade(
    private val optimisticLockStockService: OptimisticLockStockService,
) {

    fun decrease(id: Long, quantity: Long) {
        while (true) {
            try {
                optimisticLockStockService.decrease(id, quantity)
                break
            } catch (e: Exception) {
                println("Throw Exception => $e.message")
                Thread.sleep(50)
            }
        }
    }
}