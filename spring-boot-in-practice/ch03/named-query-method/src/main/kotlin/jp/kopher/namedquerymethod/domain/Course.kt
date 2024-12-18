package jp.kopher.namedquerymethod.domain

import jakarta.persistence.*

@Entity
@Table(name = "COURSES")
@NamedQuery(name = "Course.findAllByCategoryAndRating", query = "select c from Course c where c.category =?1 and c.rating =?2")
class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
    private val name: String,
    private val category: String,
    private val rating: Int,
    private val description: String,
) {
}
