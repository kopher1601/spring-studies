package jp.co.kopher1601.springadvancedkotlin.app.v1

class OrderServiceV1Impl constructor(
    private val orderRepository: OrderRepositoryV1,
): OrderServiceV1 {

    override fun orderItem(itemId: String) {
        orderRepository.save(itemId)
    }

}