package jp.co.kopher1601.springadvancedkotlin.app.v2

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@RequestMapping
@ResponseBody
class OrderControllerV2(
    private val orderService: OrderServiceV2,
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/v2/request")
    fun request(itemId: String): String {
        orderService.orderItem(itemId)
        return "ok"
    }

    @GetMapping("/v2/no-log")
    fun noLog(): String {
        return "ok"
    }
}