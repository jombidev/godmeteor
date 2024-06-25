package dev.jombi.godmeteor.pcroom;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
            return parse(response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to fetch data", e);
        }
    }
//제이슨 파싱
    private String parse(String responseBody) {
        try {
//            JsonNode root = objectMapper.readValue(json,PcRoomJson);
            JsonNode root = objectMapper.readTree(responseBody);
            JsonNode gameRankNode = root.path("gameRank");
            String gameRank = gameRankNode.asText();
            return gameRank;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse data", e);
        }
    }
}
//싱글톤? 빈 생성
@Configuration
class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }
}
