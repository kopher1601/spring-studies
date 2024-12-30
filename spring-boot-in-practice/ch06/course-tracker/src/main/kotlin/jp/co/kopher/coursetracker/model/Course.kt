package jp.co.kopher.coursetracker.model

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty

@Entity
@Table(name = "COURSES")
class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    val id: Long? = null,

    @Column(name = "NAME")
    @NotEmpty(message = "Course name field can't be empty")
    val name: String? = null,

    @Column(name = "CATEGORY")
    @NotEmpty(message = "Course category field can't be empty")
    val category: String? = null,

    @Column(name = "RATING")
    @Min(value = 1)
    @Max(value = 5)
    val rating: Double? = null,

    @Column(name = "DESCRIPTION")
    @NotEmpty(message = "Course description field can't be empty")
    val description: String? = null,
)