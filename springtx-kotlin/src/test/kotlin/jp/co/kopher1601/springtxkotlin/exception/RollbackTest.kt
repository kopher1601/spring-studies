package jp.co.kopher1601.springtxkotlin.exception

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class RollbackTest @Autowired constructor(
    private val rollbackService: RollbackService,
) {

    @Test
    fun runtimeException() {
        assertThatThrownBy {
            rollbackService.runtimeException()
        }.isInstanceOf(RuntimeException::class.java)
    }

    @Test
    fun checkedException() {
        assertThatThrownBy {
            rollbackService.checkedException()
        }.isInstanceOf(MyException::class.java)
    }

    @Test
    fun rollbackFor() {
        assertThatThrownBy {
            rollbackService.rollbackFor()
        }.isInstanceOf(MyException::class.java)
    }

    @TestConfiguration
    class RollbackTestConfig {
        @Bean
        fun rollbackService(): RollbackService {
            return RollbackService()
        }
    }

    open class RollbackService {
        val log = LoggerFactory.getLogger(RollbackTest::class.java)

        // 런타임 예외 발생 : 롤백
        @Transactional
        open fun runtimeException() {
            log.info("call runtimeException")
            throw RuntimeException()
        }

        // 체크 예외 발생 : 커밋
        @Transactional
        open fun checkedException() {
            log.info("call checkedException")
            throw MyException()
        }

        // 체크 예외 rollbackFor 지정 : 롤백
        @Transactional(rollbackFor = [MyException::class])
        open fun rollbackFor() {
            log.info("call rollbackFor")
            throw MyException()
        }
    }

    class MyException : Exception()
}