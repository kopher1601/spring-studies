package jp.co.kopher.board.article.controller

import jp.co.kopher.board.article.service.ArticleService
import jp.co.kopher.board.article.service.request.ArticleCreateRequest
import jp.co.kopher.board.article.service.request.ArticleUpdateRequest
import jp.co.kopher.board.article.service.response.ArticleResponse
import org.springframework.web.bind.annotation.*

@RestController
class ArticleController(
    private val articleService: ArticleService,
) {

    @GetMapping("/v1/articles/{articleId}")
    fun read(@PathVariable articleId: Long): ArticleResponse {
        return articleService.read(articleId)
    }

    @PostMapping("/v1/articles")
    fun create(@RequestBody request: ArticleCreateRequest): ArticleResponse {
        return articleService.create(request)
    }

    @PutMapping("/v1/articles/{articleId}")
    fun update(@PathVariable articleId: Long, @RequestBody request: ArticleUpdateRequest): ArticleResponse {
        return articleService.update(articleId, request)
    }

    @DeleteMapping("/v1/articles/{articleId}")
    fun delete(@PathVariable articleId: Long) {
        articleService.delete(articleId)
    }
}