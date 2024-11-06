package jp.co.kopher1601.springadvancedkotlin.app.v3

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerV3(
    private val orderService: OrderServiceV3,
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/v3/request")
    fun request(itemId: String): String {
        orderService.orderItem(itemId)
        return "ok"
    }

    @GetMapping("/v3/no-log")
    fun noLog(): String {
        return "ok"
    }
}