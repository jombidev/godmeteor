package dev.jombi.godmeteor.global.handler;

import dev.jombi.godmeteor.global.exception.CustomException;
import dev.jombi.godmeteor.global.exception.ExceptionResponse;
import dev.jombi.godmeteor.global.exception.GlobalExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> exception(Exception e) {
        log.error("Error while processing request: ", e);
        return ExceptionResponse.of(GlobalExceptionCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> customException(CustomException e) {
        return ExceptionResponse.of(e.getDetail());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionResponse> missingParameter(MissingServletRequestParameterException ignored) {
        return ExceptionResponse.of(GlobalExceptionCode.PARAMETER_NOT_MATCH);
    }
}
