package jp.co.kopher1601.springtxkotlin.apply

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.aop.support.AopUtils
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager


@SpringBootTest
class AopUtilsTest(
    private val exampleService: ExampleService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Test
    fun `AOP proxy check`() {
        log.info("exampleService class = {}", exampleService.javaClass)

        // AOP 프록시가 적용되었는지 확인
        val isAopProxy = AopUtils.isAopProxy(exampleService)
        log.info("is AOP Proxy = {}", isAopProxy)

        assertThat(isAopProxy).isTrue
    }

    @Test
    fun `Transaction check in transactional method`() {
        exampleService.transactionalMethod()
    }

    @Test
    fun `Transaction check in non-transactional method`() {
        exampleService.nonTransactionalMethod()
    }

    @Configuration
    class AopUtilsTestConfig {
        @Bean
        fun exampleService() = ExampleService()
    }

    @Service
    open class ExampleService {
        private val log = LoggerFactory.getLogger(javaClass)

        @Transactional
        open fun transactionalMethod() {
            log.info("Calling transactional method")
            val isTxActive = TransactionSynchronizationManager.isActualTransactionActive()
            log.info("Transaction active = {}", isTxActive)
        }

        open fun nonTransactionalMethod() {
            log.info("Calling non-transactional method")
            val isTxActive = TransactionSynchronizationManager.isActualTransactionActive()
            log.info("Transaction active = {}", isTxActive)
        }
    }
}