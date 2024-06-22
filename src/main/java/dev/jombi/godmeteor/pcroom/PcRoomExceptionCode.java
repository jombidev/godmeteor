package dev.jombi.godmeteor.pcroom;

import dev.jombi.godmeteor.global.exception.ExceptionDetail;
import org.springframework.http.HttpStatus;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public enum PcRoomExceptionCode implements ExceptionDetail {
    Not_Found_PcRoom_Rank("피시방 순위 못가져옴", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    PcRoomExceptionCode(String message, HttpStatus status) {
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
