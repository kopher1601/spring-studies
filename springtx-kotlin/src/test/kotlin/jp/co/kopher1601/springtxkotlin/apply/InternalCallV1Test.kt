package jp.co.kopher1601.springtxkotlin.apply

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory.getLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager

@SpringBootTest
class InternalCallV1Test @Autowired constructor(
    val callService: CallService
) {
    val log = getLogger(this.javaClass)

    @Test
    fun printProxy() {
        log.info("callService class = {}", callService.javaClass)
    }

    @Test
    fun internalCall() {
        callService.internal()
    }

    @Test
    fun externalCall() {
        callService.external()
    }

    @TestConfiguration
    class InternalCallV1TestConfig {
        @Bean
        fun callService(): CallService {
            return CallService()
        }
    }

    open class CallService {
        val log = getLogger(this.javaClass)

        open fun external() {
            log.info("call external")
            printTxInfo()
            internal()
        }

        @Transactional
        open fun internal() {
            log.info("call internal")
            printTxInfo()
        }

        open fun printTxInfo() {
            val txActive = TransactionSynchronizationManager.isActualTransactionActive()
            log.info("tx active = {}", txActive)
        }
    }
}