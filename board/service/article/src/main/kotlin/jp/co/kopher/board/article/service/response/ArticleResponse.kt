package jp.co.kopher.board.article.service.response

import jp.co.kopher.board.article.entity.Article
import java.time.LocalDateTime

data class ArticleResponse(
    val articleId: Long,
    var title: String,
    var content: String,
    val boardId: Long, // shard key
    val writerId: Long,
    val createdAt: LocalDateTime,
    var modifiedAt: LocalDateTime,
) {
    companion object {
        fun from(article: Article): ArticleResponse {
            return ArticleResponse(
                articleId = article.articleId,
                title = article.title,
                content = article.content,
                boardId = article.boardId,
                writerId = article.writerId,
                createdAt = article.createdAt,
                modifiedAt = article.modifiedAt,
            )
        }
    }
}