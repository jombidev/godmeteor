package dev.jombi.godmeteor.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
    private final ExceptionDetail detail;
    private Object[] formats = new Object[0];

    public CustomException setFormats(Object... formats) {
        this.formats = formats;
        return this;
    }
}
