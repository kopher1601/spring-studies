package jp.kopher.springsecurityinaction.services

import jp.kopher.springsecurityinaction.model.Product
import org.springframework.security.access.prepost.PreFilter
import org.springframework.stereotype.Service

@Service
class ProductService() {

    @PreFilter("filterObject.owner == authentication.name")
    fun sellProducts(products: List<Product>): List<Product> {
        return products
    }

}