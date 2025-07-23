package kopher.board.article.service.response;

import kopher.board.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
public class ArticleResponse {
    private Long articleId;
    private String title;
    private String content;
    private Long writerId;
    private Long boardId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ArticleResponse from(Article article) {
        return new ArticleResponse(
                article.getArticleId(),
                article.getTitle(),
                article.getContent(),
                article.getWriterId(),
                article.getBoardId(),
                article.getCreatedAt(),
                article.getModifiedAt()
        );
    }
}
