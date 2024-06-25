package dev.jombi.godmeteor.pcroom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PcRoomJson {
    private List<GameRank> gameRank;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GameRank {
        private int gameRank;

        private String gameName;
        private long timeCountTotal;
        private int gameRankUpDown;
    }
}