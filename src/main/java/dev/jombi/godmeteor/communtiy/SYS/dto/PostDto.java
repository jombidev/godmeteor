package dev.jombi.godmeteor.communtiy.SYS.dto;

import lombok.*;
import dev.jombi.godmeteor.communtiy.JJE.entity.Post;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Getter @Setter
public class PostDto {

    private Long id;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public PostDto(Long id, String writer, String title, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Post ToEntity() {
        return Post.builder()
                .writer(this.writer)
                .title(this.title)
                .contents(this.contents)
                .build();
    }
}