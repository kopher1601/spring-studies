package jp.kopher1601.springsecurity.controller

import jp.kopher1601.springsecurity.model.Customer
import jp.kopher1601.springsecurity.repository.CustomerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val customerRepository: CustomerRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    @PostMapping("/register")
    fun registerUser(
        @RequestBody customer: Customer,
    ): ResponseEntity<String> {
        try {
            val hashedPwd = passwordEncoder.encode(customer.pwd)
            customer.pwd = hashedPwd
            val savedCustomer = customerRepository.save(customer)

            if (savedCustomer.id!! > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body(
                    "Registered user id: ${savedCustomer.id}"
                )
            }

            return ResponseEntity.ok("OK")
        } catch (e: Exception) {
            return ResponseEntity.internalServerError().body(e.message)
        }
    }
}