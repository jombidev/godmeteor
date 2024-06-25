package dev.jombi.godmeteor.communtiy.SYS.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostResponseDto {

    private Long id;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public PostResponseDto(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }
}
