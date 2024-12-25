package jp.kopher.customloginpage.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty

@Entity
@Table(name = "COURSES")
class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private var _id: Long? = null,

    @Column(name = "NAME")
    @NotEmpty(message = "Course name field can't be empty")
    val name: String,

    @Column(name = "CATEGORY")
    @NotEmpty(message = "Course category field can't be empty")
    val category: String,

    @Column(name = "RATING")
    @Min(value = 1)
    @Max(value = 5)
    val rating: Int,

    @Column(name = "DESCRIPTION")
    @NotEmpty(message = "Course description field can't be empty")
    val description: String,
) {

    var id: Long?
        get() = _id ?: 0
        set(value) {
            _id = value
        }
}
