package jp.kopher1601.springsecurity.repository

import jp.kopher1601.springsecurity.model.Customer
import org.springframework.data.repository.CrudRepository

interface CustomerRepository: CrudRepository<Customer, Long> {

    fun findByEmail(email: String): Customer?

}