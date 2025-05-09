package jp.co.kopher.kotlinspringtx.order

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository,
) {

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    // JPA 는 트랜잭션 커밋 시점에 Order 데이터를 DB에 반영한다.
    @Transactional
    fun order(order: Order) {
        log.info("order 호출")
        orderRepository.save(order)

        log.info("결제 프로세스 진입")
        if (order.username == "예외") {
            log.info("결제 예외 발생")
            throw RuntimeException("결제 예외 발생")
        } else if (order.username == "잔고부족") {
            log.info("잔고 부족 비즈니스 예외 발생")
            order.payStatus = "대기"
            throw NotEnoughMoneyException("잔고가 부족합니다.")
        } else {
            // 정상 승인
            log.info("정상 승인")
            order.payStatus = "완료"
        }
        log.info("결제 프로세스 완료")
    }
}