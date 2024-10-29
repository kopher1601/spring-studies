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
class InternalCallV2Test @Autowired constructor(
    val callService: CallService
) {
    val log = getLogger(this.javaClass)

    @Test
    fun printProxy() {
        log.info("callService class = {}", callService.javaClass)
    }

    @Test
    fun externalCallV2() {
        callService.external()
    }

    @TestConfiguration
    class InternalCallV1TestConfig {
        @Bean
        fun callService(): CallService {
            return CallService(internalService())
        }

        @Bean
        fun internalService(): InternalService {
            return InternalService()
        }
    }

    open class CallService @Autowired constructor(
        private val internalService: InternalService,
    ) {
        val log = getLogger(this.javaClass)

        open fun external() {
            log.info("call external")
            printTxInfo()
            internalService.internal()
        }

        open fun printTxInfo() {
            val txActive = TransactionSynchronizationManager.isActualTransactionActive()
            log.info("tx active = {}", txActive)
        }
    }

    open class InternalService {
        val log = getLogger(this.javaClass)

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