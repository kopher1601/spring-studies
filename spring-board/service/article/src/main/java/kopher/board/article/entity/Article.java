package kopher.board.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "article")
@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Article {
    @Id
    private Long articleId;

    private String title;

    private String content;

    private Long boardId;

    private Long writerId;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public static Article create(Long articleId, String title, String content, Long boardId, Long writerId) {
        return new Article(
                articleId,
                title,
                content,
                boardId,
                writerId,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.modifiedAt = LocalDateTime.now();
    }
}
