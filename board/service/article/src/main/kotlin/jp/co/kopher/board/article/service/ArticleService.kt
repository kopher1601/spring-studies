package jp.co.kopher.board.article.service

import board.common.snowflake.Snowflake
import jp.co.kopher.board.article.entity.Article
import jp.co.kopher.board.article.repository.ArticleRepository
import jp.co.kopher.board.article.service.request.ArticleCreateRequest
import jp.co.kopher.board.article.service.request.ArticleUpdateRequest
import jp.co.kopher.board.article.service.response.ArticleResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ArticleService(
    private val articleRepository: ArticleRepository
) {
    private val snowflake: Snowflake = Snowflake()

    fun create(request: ArticleCreateRequest): ArticleResponse {
        val article = articleRepository.save(
            Article.create(snowflake.nextId(), request.title, request.content, request.boardId, request.writerId)
        )
        return ArticleResponse.from(article)
    }

    fun update(articleId: Long, request: ArticleUpdateRequest): ArticleResponse {
        val article = articleRepository.findByIdOrNull(articleId)
            ?: throw IllegalArgumentException("Article not found")
        article.update(request.title, request.content)
        return ArticleResponse.from(article)
    }

    @Transactional(readOnly = true)
    fun read(articleId: Long): ArticleResponse {
        return ArticleResponse.from(
            articleRepository.findByIdOrNull(articleId)
                ?: throw IllegalArgumentException("Article not found")
        )
    }

    fun delete(articleId: Long) {
        articleRepository.deleteById(articleId)
    }
}