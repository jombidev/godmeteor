package dev.jombi.godmeteor.pcroom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jombi.godmeteor.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@RequiredArgsConstructor
@Service
public class PcRoomGetLank {

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;
//제이슨 가져오기
    public String get() {
        String url = "https://www.thelog.co.kr/api/common/getCommonState.do?gameDataType=S";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return parse(response.body()).toString();
        } catch (IOException | InterruptedException e) {
            throw new CustomException(PcRoomExceptionCode.Not_Found_PcRoom_Rank);
        }
    }
//제이슨 파싱
    private PcRoomJson parse(String responseBody) {
        try {
            PcRoomJson pcRoomJson = objectMapper.readValue(responseBody,PcRoomJson.class);
//            JsonNode root = objectMapper.readTree(responseBody);
//            JsonNode gameRankNode = root.path("gameRank");
//            String gameRank = gameRankNode.asText();
            return pcRoomJson;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse data", e);
        }
    }
}
