package jp.co.kopher1601.springadvancedkotlin.app.v2

class OrderServiceV2 constructor(
    private val orderRepository: OrderRepositoryV2,
){

    fun orderItem(itemId: String) {
        orderRepository.save(itemId)
    }

}