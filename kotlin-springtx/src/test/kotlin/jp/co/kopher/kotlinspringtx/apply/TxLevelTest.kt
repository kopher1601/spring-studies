package jp.co.kopher.kotlinspringtx.apply

import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class TxLevelTest(
    private val service: LevelService,
) {

    @Test
    fun orderTest() {
        service.write() // tx active = true, read only = false
        service.read()  // tx active = true, read only = true
    }

    @TestConfiguration
    class TxLevelTestConfig {
        @Bean
        fun levelService(): LevelService {
            return LevelService()
        }
    }

    @Transactional(readOnly = true)
    class LevelService {

        val log: Logger = LoggerFactory.getLogger(TxLevelTest::class.java)

        @Transactional(readOnly = false)
        fun write() {
            log.info("call write")
            printTxInfo()
        }

        fun read() {
            log.info("call read")
            printTxInfo()
        }

        private fun printTxInfo() {
            val txActive = TransactionSynchronizationManager.isActualTransactionActive()
            log.info("tx active = {}", txActive)
            val readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly()
            log.info("read only = {}", readOnly)
        }
    }
}