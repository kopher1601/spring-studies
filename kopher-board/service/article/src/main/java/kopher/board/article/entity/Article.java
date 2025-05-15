package kopher.board.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Table(name = "article")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    private Long articleId;

    private String title;
    private String content;
    private Long boardId; // shard key
    private Long writerId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    private Article(Long articleId, String title, String content, Long boardId, Long writerId, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.boardId = boardId;
        this.writerId = writerId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static Article create(Long articleId, String title, String content, Long boardId, Long writerId) {
        return Article.builder()
                .articleId(articleId)
                .title(title)
                .content(content)
                .boardId(boardId)
                .writerId(writerId)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.modifiedAt = LocalDateTime.now();
    }
}
