package jp.co.kopher.board.article.repository

import jp.co.kopher.board.article.entity.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository: JpaRepository<Article, Long> {
}