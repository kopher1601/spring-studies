package jp.kopher.customfailureanalyzer.entity

import jakarta.persistence.*

@Entity
@Table(name = "COURSES")
class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private val id: Long? = null,

    @Column(name = "NAME")
    private val name: String? = null,

    @Column(name = "CATEGORY")
    private val category: String? = null,

    @Column(name = "RATING")
    private val rating: Int = 0,

    @Column(name = "DESCRIPTION")
    private val description: String? = null,
)
