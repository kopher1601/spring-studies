package jp.kopher.managingmanytomany.domain

import jakarta.persistence.*

@Entity(name = "COURSES")
@Table(name = "COURSES")
class Course(
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @Column(name = "NAME")
    private val name: String,

    @Column(name = "CATEGORY")
    private val category: String,

    @Column(name = "RATING")
    private val rating: Int,

    @Column(name = "DESCRIPTION")
    private val description: String,

    @ManyToMany(mappedBy = "courses")
    private val authors: Set<Author> = HashSet()

) {
}
