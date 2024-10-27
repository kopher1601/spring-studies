package jp.co.kopher1601.stockexample.service

import jp.co.kopher1601.stockexample.domain.Stock
import jp.co.kopher1601.stockexample.repository.StockRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class StockServiceTest(
    private val stockService: StockService,
    private val pessimisticLockStockService: PessimisticLockStockService,
    private val stockRepository: StockRepository,
) {

    @BeforeEach
    fun setUp() {
        stockRepository.saveAndFlush(Stock(1L, 100L))
    }

    @AfterEach
    fun tearDown() {
        stockRepository.deleteAll()
    }

    @Test
    fun decrease() {
        stockService.decrease(1L, 1L)

        // 100 - 1 = 99
        val stock = stockRepository.findById(1L).get()
        assertEquals(99, stock.quantity)
    }

    @Test
    fun decreaseConcurrent() {
        val threadCount = 100
        // 멀티스레드 이용을 위함
        // ExecutorService 는 비동기로 실행하는 작업을 단순화하여 사용할 수 있게 도와주는 자바의 API 이다.
        val executorService = Executors.newFixedThreadPool(32)

        // CountDownLatch 는 다른 스레드에서 수행중인 작업을 대기할수 있도록 도와주는 클래스이다.
        val latch = CountDownLatch(threadCount)

        for (i in 0..threadCount) {
            executorService.submit {
                try {
                    pessimisticLockStockService.decrease(1L, 1L)
                } finally {
                    latch.countDown()
                }
            }
        }
        latch.await()

        val stock = stockRepository.findById(1L).get()
        // ecpect : 100 - (1 * 100) = 0
        // actual : 94
        // 이유는 race condition 이 발생했기 때문이다.
        // race condition 이란 둘 이상의 스레드가 공유 데이터에 액세스 할 수 있고 동시에 변경하려고 발생하는 문제이다.
        assertEquals(0, stock.quantity)
    }
}