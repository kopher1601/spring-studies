package jp.kopher.managingmanytomany.domain

import jakarta.persistence.*

@Entity(name = "AUTHOR")
@Table(name = "AUTHORS")
class Author(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    private val name: String,
    private val bio: String,

    @ManyToMany
    @JoinTable(
        name = "authors_courses",
        joinColumns = [JoinColumn(
            name = "author_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
        )],
        inverseJoinColumns = [JoinColumn(
            name = "course_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
        )]
    )
    private val courses: Set<Course> = HashSet(),
) {
}
