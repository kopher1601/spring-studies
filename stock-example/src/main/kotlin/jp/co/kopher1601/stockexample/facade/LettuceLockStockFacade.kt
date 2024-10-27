package jp.co.kopher1601.stockexample.facade

import jp.co.kopher1601.stockexample.repository.RedisLockRepository
import jp.co.kopher1601.stockexample.service.StockService
import org.springframework.stereotype.Component

@Component
class LettuceLockStockFacade(
    private val redisLockRepository: RedisLockRepository,
    private val stockService: StockService,
) {

    fun decrease(id: Long, quantity: Long) {
        // while 문을 이용하여 락 획득 시도
        while (!redisLockRepository.lock(id)) {

            // 락 획득 실패시 100mm 세컨드 대기후 재시도 (redis 부하를 줄이기위해)
            Thread.sleep(100)
        }

        try {
            stockService.decrease(id, quantity)
        } finally {
            redisLockRepository.unlock(id)
        }
    }
}