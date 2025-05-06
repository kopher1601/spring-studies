package jp.co.kopher.kotlinspringtx.apply

import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class InternalCallV1Test(
    private val callService: CallService
) {

    val log: Logger = LoggerFactory.getLogger(InternalCallV1Test::class.java)

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

    @Component
    class CallService {

        val log: Logger = LoggerFactory.getLogger(CallService::class.java)

        fun external() {
            log.info("call external")
            printTxInfo()
            internal()
        }

        @Transactional
        fun internal() {
            log.info("call internal")
            printTxInfo()
        }

        private fun printTxInfo() {
            val txActive = TransactionSynchronizationManager.isActualTransactionActive()
            log.info("tx active = {}", txActive)
        }
    }
}