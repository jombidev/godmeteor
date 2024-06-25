package dev.jombi.godmeteor.communtiy.SYS.dto;

import lombok.*;
import dev.jombi.godmeteor.communtiy.JJE.entity.Post;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter @Setter
@Builder
public class PostRequestDto {

    private String writer;
    private String title;
    private String contents;

    public Post toEntity() {
        return Post.builder()
                .writer(this.writer)
                .title(this.title)
                .contents(this.contents)
                .build();
    }
}