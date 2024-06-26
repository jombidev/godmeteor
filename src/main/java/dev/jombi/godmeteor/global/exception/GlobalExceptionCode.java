package dev.jombi.godmeteor.global.exception;

import org.springframework.http.HttpStatus;

public enum GlobalExceptionCode implements ExceptionDetail {
    INTERNAL_SERVER_ERROR("내부 서버 에러", HttpStatus.INTERNAL_SERVER_ERROR),
    PARAMETER_NOT_MATCH("파라미터 불일치: %s (%s)", HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND("리소스 '/%s' 를 찾을 수 없음", HttpStatus.NOT_FOUND),
    ;

    private final String message;
    private final HttpStatus status;

    GlobalExceptionCode(String message, HttpStatus status) {
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
