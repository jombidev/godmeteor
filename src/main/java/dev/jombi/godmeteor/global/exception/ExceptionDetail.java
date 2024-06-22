package dev.jombi.godmeteor.global.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionDetail {
    String getMessage();
    HttpStatus getStatus();
    String getEnumName();
}
