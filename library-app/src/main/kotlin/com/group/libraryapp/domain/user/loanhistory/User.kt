package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.domain.book.Book
import javax.persistence.*

@Entity
class User(
    var name: String,

    val age: Int?,

    @OneToMany(cascade = [(CascadeType.ALL)], orphanRemoval = true)
    val userLoanHistories: MutableList<UserLoanHistory> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {


    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("Name cannot be blank")
        }
    }

    fun updateName(name: String) {
        this.name = name
    }

    fun loanBook(book: Book) {
        this.userLoanHistories.add(UserLoanHistory(this, book.name, false))
    }

    fun returnBook(bookName: String) {
        this.userLoanHistories.first { history -> history.bookName == bookName }.doReturn()
    }

}