package jp.kopher.springsecurityinaction.controllers

import jp.kopher.springsecurityinaction.model.Product
import jp.kopher.springsecurityinaction.services.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    val productService: ProductService,
) {

    @GetMapping("/sell")
    fun sellProduct(): List<Product> {
        return productService.sellProducts(
            // listOf では filtering が動作しない
            mutableListOf(
                Product("beer", "nikolai"),
                Product("candy", "nikolai"),
                Product("chocolate", "julien"),
            )
        )
    }
}