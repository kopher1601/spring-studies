package com.group.libraryapp.domain.book

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Book(
    val name: String,

    val type: String,

    // default 値が入るパラメーターは一番したにあるのが慣例
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
) {

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("Book name cannot be blank")
        }
    }

    // object mother pattern
    companion object {
        fun fixture(
            name: String = "book name",
            type: String = "book type",
            id: Long? = null,
        ): Book {
            return Book(
                name = name,
                type = type,
                id = id,
            )
        }
    }

}