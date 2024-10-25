package com.group.libraryapp.domain.book

import javax.persistence.*

@Entity
class Book(
    val name: String,

    @Enumerated(EnumType.STRING)
    val type: BookType,

    // 기본값을 받는 필드는 가장 밑에 두는게 코틀린의 관례이다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("Book name cannot be blank")
        }
    }

    companion object {
        fun fixture(name: String = "책이름", type: BookType = BookType.COMPUTER, id: Long? = null): Book {
            return Book(
                name = name,
                type = type,
                id = id,
            )
        }
    }
}