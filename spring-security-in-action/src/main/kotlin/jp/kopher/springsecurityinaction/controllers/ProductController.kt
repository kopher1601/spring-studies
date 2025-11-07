package jp.kopher.springsecurityinaction.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/product")
class ProductController {

    private val log = org.slf4j.LoggerFactory.getLogger(this::class.java)

    @PostMapping("/add")
    fun add(@RequestParam name: String): String {
        log.info("add product: $name")
        return "main"
    }
}