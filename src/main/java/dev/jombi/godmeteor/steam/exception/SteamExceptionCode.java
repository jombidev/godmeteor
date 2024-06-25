package dev.jombi.godmeteor.steam.exception;

import dev.jombi.godmeteor.global.exception.ExceptionDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum SteamExceptionCode implements ExceptionDetail {
    GET_STEAM_CHART_FAILED("스팀 차트를 가져올 수 없음", HttpStatus.INTERNAL_SERVER_ERROR),
    STEAM_CHART_PARSE_FAILED("스팀 차트를 가져오는 도중 문제 발생", HttpStatus.INTERNAL_SERVER_ERROR),
    GAME_INFO_FETCH_FAILED("게임들을 가져오는 도중 문제 발생", HttpStatus.INTERNAL_SERVER_ERROR),
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
