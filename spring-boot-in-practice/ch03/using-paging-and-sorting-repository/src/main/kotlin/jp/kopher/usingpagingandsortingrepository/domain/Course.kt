package jp.kopher.usingpagingandsortingrepository.domain

import jakarta.persistence.*

@Table(name = "COURSES")
@Entity
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,
    val category: String,
    val rating: Int,
    val description: String,
)
