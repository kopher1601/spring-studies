package jp.co.kopher.board.article.data

import board.common.snowflake.Snowflake
import jakarta.persistence.EntityManager
import jp.co.kopher.board.article.entity.Article
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.support.TransactionTemplate
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest(properties = ["spring.jpa.show-sql=false"])
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class DataInitializer(
    private val entityManager: EntityManager,
    private val transactionTemplate: TransactionTemplate,
) {
    private val snowflake: Snowflake = Snowflake()
    private val latch: CountDownLatch = CountDownLatch(EXECUTE_COUNT)

    @Test
    fun initialize() {
        val executorService = Executors.newFixedThreadPool(10)
        repeat(EXECUTE_COUNT) {
            executorService.submit {
                insert()
                latch.countDown()
                println("latch.count = ${latch.count}")
            }
        }
        latch.await()
        executorService.shutdown()
    }

    fun insert() {
        transactionTemplate.executeWithoutResult {
            repeat(BULK_INSERT_SIZE) {
                entityManager.persist(
                    Article.create(
                        snowflake.nextId(),
                        "title $it",
                        "content $it",
                        1L,
                        1L,
                    )
                )
            }
        }
    }

    companion object {
        const val EXECUTE_COUNT = 6000
        const val BULK_INSERT_SIZE = 2000
    }
}