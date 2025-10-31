package jp.kopher.springsecurityinaction.services

import jp.kopher.springsecurityinaction.entities.Product
import jp.kopher.springsecurityinaction.repositories.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {

    fun findAll(): List<Product> {
        return productRepository.findAll()
    }
}