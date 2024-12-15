package jp.co.kopher1601.beanvalidation

import jakarta.validation.ConstraintViolation
import jakarta.validation.Validation
import jp.co.kopher1601.beanvalidation.model.User
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.function.Consumer

@SpringBootApplication
class BeanValidationApplication : CommandLineRunner {

    val log = LoggerFactory.getLogger(this::class.java)

    override fun run(vararg args: String?) {
        val validator = Validation.buildDefaultValidatorFactory().validator

        val user1 = User("sbip01", "sbip")
        var violations = validator.validate(user1)
        log.error("Password for user1 do not adhere to the password policy")
        violations.forEach { constraintViolation ->
            log.error("Violation details: [{}]", constraintViolation.message)
        }

        val user2 = User("sbip02", "Sbip01$4UDfg")
        violations = validator.validate(user2)
        if (violations.isEmpty()) {
            log.error("Password for user2 adhere to the password policy")
        }

        val user3 = User("sbip03", "Sbip01$4UDfgggg")
        violations = validator.validate(user3)
        log.error("Password for user3 violates maximum repetitive rule")
        violations.forEach(Consumer<ConstraintViolation<User?>> { constraintViolation: ConstraintViolation<User?> ->
            log.error(
                "Violation details: [{}].",
                constraintViolation.message
            )
        })

        val user4 = User("sbip04", "Sbip014UDfgggg")
        violations = validator.validate(user4)
        log.error("Password for user4 violates special character rule")
        violations.forEach(Consumer<ConstraintViolation<User?>> { constraintViolation: ConstraintViolation<User?> ->
            log.error(
                "Violation details: [{}].",
                constraintViolation.message
            )
        })
    }

}

fun main(args: Array<String>) {
    runApplication<BeanValidationApplication>(*args)
}
