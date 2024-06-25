package dev.jombi.godmeteor.communtiy.exception;

import dev.jombi.godmeteor.global.exception.ExceptionDetail;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum PostExceptionCode implements ExceptionDetail {
    POST_NOT_FOUND("아이디 '%s'를 가진 게시글을 찾을 수 없음", HttpStatus.NOT_FOUND),
    ;

    private final String message;
    private final HttpStatus status;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getEnumName() {
        return name();
    }
}

