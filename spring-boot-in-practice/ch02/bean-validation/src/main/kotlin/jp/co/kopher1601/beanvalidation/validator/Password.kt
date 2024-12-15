package jp.co.kopher1601.beanvalidation.validator

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PasswordRuleValidator::class])
annotation class Password(
    val message: String = "Password does not adhere to the specified rule",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
