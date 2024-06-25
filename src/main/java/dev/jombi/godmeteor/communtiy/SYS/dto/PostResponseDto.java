package dev.jombi.godmeteor.communtiy.SYS.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data @Builder
@AllArgsConstructor
public class PostResponseDto {

    private Long id;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
