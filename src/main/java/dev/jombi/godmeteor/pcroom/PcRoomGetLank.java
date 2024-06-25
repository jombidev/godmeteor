package dev.jombi.godmeteor.pcroom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jombi.godmeteor.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


@RequiredArgsConstructor
@Service
public class PcRoomGetLank {

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;
//제이슨 가져오기
    public List<PcRoomJson.GameRank> get() {//toString을 보내면 안되기 때문 반환값 변경함
        String url = "https://www.thelog.co.kr/api/common/getCommonState.do?gameDataType=S";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return parse(response.body()).getGameRank();
        } catch (IOException | InterruptedException e) {
            throw new CustomException(PcRoomExceptionCode.Not_Found_PcRoom_Rank);
        }
    }
//제이슨 파싱
    private PcRoomJson parse(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, PcRoomJson.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse data", e);
        }
    }
}
