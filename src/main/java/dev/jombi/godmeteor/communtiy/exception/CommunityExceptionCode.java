package dev.jombi.godmeteor.communtiy.exception;

import dev.jombi.godmeteor.global.exception.ExceptionDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum CommunityExceptionCode implements ExceptionDetail {
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