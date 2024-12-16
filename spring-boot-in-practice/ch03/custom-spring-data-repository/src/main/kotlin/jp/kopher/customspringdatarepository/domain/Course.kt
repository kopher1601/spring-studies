package jp.kopher.customspringdatarepository.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    private val name: String,
    private val category: String,
    private val rating: Int,
    private val description: String,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Course

        if (id != other.id) return false
        if (rating != other.rating) return false
        if (name != other.name) return false
        if (category != other.category) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + rating
        result = 31 * result + name.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }

    override fun toString(): String {
        return "Course(id=$id, name='$name', category='$category', rating=$rating, description='$description')"
    }


}
