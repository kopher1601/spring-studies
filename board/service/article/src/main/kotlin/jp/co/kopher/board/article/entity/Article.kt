package jp.co.kopher.board.article.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "article")
@Entity
class Article(
    var title: String,
    var content: String,
    val boardId: Long, // shard key
    val writerId: Long,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime,

    @Id
    val articleId: Long,
) {

    fun update(title: String, content: String) {
        this.title = title
        this.content = content
        this.modifiedAt = LocalDateTime.now()
    }

    companion object {
        fun create(
            articleId: Long,
            title: String,
            content: String,
            boardId: Long,
            writerId: Long,
        ): Article {
            return Article(
                articleId = articleId,
                title = title,
                content = content,
                boardId = boardId,
                writerId = writerId,
                createdAt = LocalDateTime.now(),
                modifiedAt = LocalDateTime.now(),
            )
        }
    }
}