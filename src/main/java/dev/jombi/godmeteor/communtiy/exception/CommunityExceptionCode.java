package dev.jombi.godmeteor.communtiy.exception;

import dev.jombi.godmeteor.global.exception.ExceptionDetail;
import org.springframework.http.HttpStatus;

public enum CommunityExceptionCode implements ExceptionDetail {
    INTERNAL_SERVER_ERROR("오류", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    CommunityExceptionCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

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