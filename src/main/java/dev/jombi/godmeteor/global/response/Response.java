package dev.jombi.godmeteor.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class Response {
    private final String message;
    private final int status;

    public static ResponseEntity<Response> of(String message, HttpStatus code) {
        return ResponseEntity.status(code).body(new Response(message, code.value()));
    }

    public static ResponseEntity<Response> ok(String message) {
        return of(message, HttpStatus.OK);
    }
}
