package jp.co.kopher1601.beanvalidation

import jakarta.validation.Validation
import jp.co.kopher1601.beanvalidation.model.Course
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BeanValidationApplication : CommandLineRunner {

    val log = LoggerFactory.getLogger(this::class.java)

    override fun run(vararg args: String?) {
        val course = Course(
            id = 1L,
            rating = 0
        )

        val validator = Validation.buildDefaultValidatorFactory().validator
        val violations = validator.validate(course)

        log.info("is it work?")
        violations.forEach { violation ->
            log.error("A constraint violation has occurred. Violation details: [{}]", violation)
        }
    }

}

fun main(args: Array<String>) {
    runApplication<BeanValidationApplication>(*args)
}
