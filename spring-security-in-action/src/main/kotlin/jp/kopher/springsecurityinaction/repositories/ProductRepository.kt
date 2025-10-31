package jp.kopher.springsecurityinaction.repositories

import jp.kopher.springsecurityinaction.entities.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Int> {
}