package kopher.board.article.repository;

import kopher.board.article.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @Test
    void findAllTest() {
        List<Article> articles = articleRepository.findAll(1L, 1499970L, 30L);
        log.info("articles = {}", articles.size());
        articles.forEach(article -> log.info("article = {}", article));
    }

    @Test
    void countTest() {
        Long count = articleRepository.count(1L, 10000L);
        log.info("count = {}", count);
    }

    @Test
    void findAllInfiniteScrollTest() {
        List<Article> articles = articleRepository.findAllInfiniteScroll(1L, 30L);
        articles.forEach(article -> log.info("article = {}", article));

        List<Article> lastArticles = articleRepository.findAllInfiniteScroll(1L, 30L, articles.getLast().getArticleId());
        log.info("lastArticles = {}", lastArticles.size());
        lastArticles.forEach(article -> log.info("lastArticle = {}", article));
    }
}