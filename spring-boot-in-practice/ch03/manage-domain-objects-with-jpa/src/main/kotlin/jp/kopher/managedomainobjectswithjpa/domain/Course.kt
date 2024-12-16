package jp.kopher.managedomainobjectswithjpa.domain

import jakarta.persistence.*

@Entity
@Table(name = "courses")
class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val _id: Long? = null,
    private val name: String,
    private val category: String,
    private var _rating: Int,
    private val description: String,
) {

    val id: Long?
        get() = _id

    var rating: Int
        get() = _rating
        set(value) {
            _rating = value
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Course

        if (_id != other._id) return false
        if (_rating != other._rating) return false
        if (name != other.name) return false
        if (category != other.category) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = _id?.hashCode() ?: 0
        result = 31 * result + _rating
        result = 31 * result + name.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }

    override fun toString(): String {
        return "Course(_id=$_id, name='$name', category='$category', _rating=$_rating, description='$description')"
    }


}
