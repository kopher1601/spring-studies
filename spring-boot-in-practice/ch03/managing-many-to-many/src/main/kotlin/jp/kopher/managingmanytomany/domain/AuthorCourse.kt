package jp.kopher.managingmanytomany.domain

import jakarta.persistence.*

@Entity(name = "AUTHORS_COURSES")
@Table(name = "AUTHORS_COURSES")
class AuthorCourse(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val authorId: Long? = null,

    @Column(name = "course_id")
    private val courseId: Long? = null,
) {
}
