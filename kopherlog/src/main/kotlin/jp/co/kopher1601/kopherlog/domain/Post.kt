package jp.co.kopher1601.kopherlog.domain

import jakarta.persistence.*
import jp.co.kopher1601.kopherlog.request.PostEdit

@Entity
class Post(

    private var _title: String,

    @Lob
    private var _content: String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {

    val title: String
        get() = this._title

    val content: String
        get() = this._content

    fun update(postEdit: PostEdit) {
        if (postEdit.title != null) {
            this._title = postEdit.title
        }

        if (postEdit.content != null) {
            this._content = postEdit.content
        }
    }
}