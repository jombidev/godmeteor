package dev.jombi.godmeteor.pcroom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PcRoomJson {

    @JsonProperty("gameRank") // JSON 필드 이름과 매핑
    private String gameRank;

    // gameRank 필드의 Getter
    public String getGameRank() {
        return gameRank;
    }

    // gameRank 필드의 Setter
    public void setGameRank(String gameRank) {
        this.gameRank = gameRank;
    }
}
