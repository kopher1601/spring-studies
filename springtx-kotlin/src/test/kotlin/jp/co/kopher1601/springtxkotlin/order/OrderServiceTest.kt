package jp.co.kopher1601.springtxkotlin.order

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class OrderServiceTest @Autowired constructor(
    private val orderService: OrderService,
    private val orderRepository: OrderRepository,
) {

    val log: Logger = LoggerFactory.getLogger(OrderService::class.java)

    @Test
    fun complete() {
        // given
        val order = Order("정상")

        // when
        orderService.order(order)

        // then
        val findOrder = orderRepository.findByIdOrNull(order.id!!)
        assertThat(findOrder).isNotNull()
        assertThat(findOrder?.payStatus).isEqualTo("완료")
    }

    @Test
    fun runtimeException() {
        // given
        val order = Order("예외")

        // when
        assertThatThrownBy { orderService.order(order) }
            .isInstanceOf(RuntimeException::class.java)

        // then
        val findOrder = orderRepository.findByIdOrNull(order.id!!)
        assertThat(findOrder).isNull()
    }

    @Test
    fun bizException() {
        // given
        val order = Order("잔고부족")

        // when
        try {
            orderService.order(order)
        } catch (e: NotEnoughMoneyException) {
            log.info("고객에게 잔고 부족을 알리고 별도의 계좌로 입금하도록 안내")
        }

        // then
        val findOrder = orderRepository.findByIdOrNull(order.id!!)
        assertThat(findOrder?.payStatus).isEqualTo("대기")
    }


}