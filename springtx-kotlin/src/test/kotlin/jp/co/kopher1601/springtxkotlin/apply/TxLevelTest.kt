package jp.co.kopher1601.springtxkotlin.apply

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager

@SpringBootTest
class TxLevelTest @Autowired constructor(
    val levelService: LevelService,
) {

    @Test
    fun orderTest() {
        levelService.write()
        levelService.read()
    }

    @TestConfiguration
    class TxLevelTestConfig {
        @Bean
        fun levelService():LevelService {
            return LevelService()
        }
    }

    @Transactional(readOnly = true)
    class LevelService {
        val log = LoggerFactory.getLogger(this.javaClass)

        @Transactional(readOnly = false)
        fun write() {
            log.info("call write")
            printTxInfo()
        }

        fun read() {
            log.info("call read")
            printTxInfo()
        }

        fun printTxInfo() {
            val txActive = TransactionSynchronizationManager.isActualTransactionActive()
            log.info("tx active = {}", txActive)
            val readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly()
            log.info("read only = {}", readOnly)
        }
    }
}