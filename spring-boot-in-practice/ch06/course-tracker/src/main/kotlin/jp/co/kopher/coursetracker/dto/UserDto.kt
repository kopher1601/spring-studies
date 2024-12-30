package jp.co.kopher.coursetracker.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

class UserDto(
    @NotEmpty(message = "Enter your firstname")
    val firstName: String? = null,

    @NotEmpty(message = "Enter your lastname")
    val lastName: String? = null,

    val username: String? = null,

    @NotEmpty(message = "Enter your email")
    @Email(message = "Email is not valid")
    val email: String? = null,

    @NotEmpty(message = "Enter a password")
    val password: String? = null,

    @NotEmpty(message = "Confirm your password")
    val confirmPassword: String? = null,
) {
}