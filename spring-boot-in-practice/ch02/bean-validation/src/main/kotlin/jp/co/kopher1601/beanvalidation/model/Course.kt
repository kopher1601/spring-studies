package jp.co.kopher1601.beanvalidation.model

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

data class Course(
    val id: Long,
    val name: String? = "",
    val category: String? = "",

    @field:Min(value = 1, message = "A course should have a minimum of 1 rating")
    @field:Max(value = 1, message = "A course should have a maximum of 5 rating")
    val rating: Int? = 0,

    val description: String? = "",
)
