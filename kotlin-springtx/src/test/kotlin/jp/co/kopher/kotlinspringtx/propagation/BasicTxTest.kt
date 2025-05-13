package jp.co.kopher.kotlinspringtx.propagation

import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.interceptor.DefaultTransactionAttribute
import javax.sql.DataSource

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class BasicTxTest(
    val txManager: PlatformTransactionManager
) {

    val log: Logger = LoggerFactory.getLogger(javaClass)

    @TestConfiguration
    class Config {
        @Bean
        fun transactionManager(dataSource: DataSource): PlatformTransactionManager {
            return DataSourceTransactionManager(dataSource)
        }
    }

    @Test
    fun commit() {
        log.info("트랜잭션 시작")
        val status = txManager.getTransaction(DefaultTransactionAttribute())

        log.info("트랜잭션 커밋 시작")
        txManager.commit(status)
        log.info("트랜잭션 커밋 완료")
    }

    @Test
    fun rollback() {
        log.info("트랜잭션 시작")
        val status = txManager.getTransaction(DefaultTransactionAttribute())

        log.info("트랜잭션 롤백 시작")
        txManager.rollback(status)
        log.info("트랜잭션 롤백 완료")
    }

    @Test
    fun double_commit() {
        log.info("트랜잭션1 시작")
        val status = txManager.getTransaction(DefaultTransactionAttribute())
        log.info("트랜잭션1 커밋")
        txManager.commit(status)

        log.info("트랜잭션2 시작")
        val status2 = txManager.getTransaction(DefaultTransactionAttribute())
        log.info("트랜잭션2 커밋")
        txManager.commit(status2)
    }

    @Test
    fun double_commit_rollback() {
        log.info("트랜잭션1 시작")
        val status = txManager.getTransaction(DefaultTransactionAttribute())
        log.info("트랜잭션1 커밋")
        txManager.commit(status)

        log.info("트랜잭션2 시작")
        val status2 = txManager.getTransaction(DefaultTransactionAttribute())
        log.info("트랜잭션2 롤백")
        txManager.rollback(status2)
    }

    @Test
    fun inner_commit() {
        log.info("외부 트랜잭션 시작")
        val outer = txManager.getTransaction(DefaultTransactionAttribute())
        log.info("outer.isNewTransaction = {}",outer.isNewTransaction)

        log.info("내부 트랜잭션 시작")
        val inner = txManager.getTransaction(DefaultTransactionAttribute())
        log.info("outer.isNewTransaction = {}",inner.isNewTransaction)

        log.info("내부 트랜잭션 커밋")
        txManager.commit(inner) // 여기서는 아무일도 안함

        log.info("외부 트랜잭션 커밋")
        txManager.commit(outer)
    }
}