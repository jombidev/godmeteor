package dev.jombi.godmeteor.pcroom;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PcRoomGetLank {
    public String get() {
        String url = "https://www.thelog.co.kr/api/common/getCommonState.do?gameDataType=S";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return parse(response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Failed to fetch data", e);
        }
    }

    private String parse(String responseBody) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(responseBody);
            JsonNode gameRankNode = root.path("gameRank");
            String gameRank = gameRankNode.asText(); // Assuming gameRank is a string
            return gameRank;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse data", e);
        }
    }
}
