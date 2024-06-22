package dev.jombi.godmeteor.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ResponseData<T> extends Response {
    private final T data;

    public ResponseData(String message, int status, T data) {
        super(message, status);
        this.data = data;
    }

    public static <T> ResponseEntity<ResponseData<T>> of(String message, HttpStatus code, T data) {
        return ResponseEntity.status(code).body(new ResponseData<>(message, code.value(), data));
    }

    public static <T> ResponseEntity<ResponseData<T>> ok(String message, T data) {
        return of(message, HttpStatus.OK, data);
    }
}
