package jp.kopher.usingquerydsl.domain

import jakarta.persistence.*

@Entity
@Table(name = "COURSES")
class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
    private val _name: String,
    private val category: String,
    private val rating: Int,
    private val description: String,
) {
    val name: String
        get() = _name
}
