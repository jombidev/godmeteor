package dev.jombi.godmeteor.global.exception;

import dev.jombi.godmeteor.global.response.Response;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ExceptionResponse extends Response {
    private final String detail;
    public ExceptionResponse(String message, int status, String detail) {
        super(message, status);
        this.detail = detail;
    }


    public static ResponseEntity<ExceptionResponse> of(ExceptionDetail e, Object... formats) {
        return ResponseEntity.status(e.getStatus()).body(new ExceptionResponse(e.getEnumName(), e.getStatus().value(), String.format(e.getMessage(), formats)));
    }
}
