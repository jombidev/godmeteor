package dev.jombi.godmeteor.communtiy.SYS.dto;

import lombok.*;
import dev.jombi.godmeteor.communtiy.JJE.entity.Post;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
public class PostRequestDto {

    private Long id;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public PostRequestDto(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Post ToEntity() {
        return Post.builder()
                .writer(this.writer)
                .title(this.title)
                .contents(this.contents)
                .build();
    }
}