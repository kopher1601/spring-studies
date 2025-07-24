package kopher.board.article.controller;

import kopher.board.article.service.ArticleService;
import kopher.board.article.service.request.ArticleCreateRequest;
import kopher.board.article.service.request.ArticleUpdateRequest;
import kopher.board.article.service.response.ArticlePageResponse;
import kopher.board.article.service.response.ArticleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/v1/articles/{articleId}")
    public ArticleResponse read(@PathVariable Long articleId) {
        return articleService.read(articleId);
    }

    @GetMapping("/v1/articles")
    public ArticlePageResponse readAll(@RequestParam Long boardId, @RequestParam Long page, @RequestParam Long pageSize) {
        return articleService.readAll(boardId, page, pageSize);
    }

    @PostMapping("/v1/articles")
    public ArticleResponse create(@RequestBody ArticleCreateRequest request) {
        return articleService.create(request);
    }

    @PutMapping("/v1/articles/{articleId}")
    public ArticleResponse update(@PathVariable Long articleId, @RequestBody ArticleUpdateRequest request) {
        return articleService.update(articleId, request);
    }

    @DeleteMapping("/v1/articles/{articleId}")
    public void delete(@PathVariable Long articleId) {
        articleService.delete(articleId);
    }
}
