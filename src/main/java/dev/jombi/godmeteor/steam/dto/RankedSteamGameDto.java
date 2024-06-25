package dev.jombi.godmeteor.steam.dto;

import dev.jombi.godmeteor.protos.CStoreTopSellers_GetWeeklyTopSellers_Response_TopSellersRank;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Builder
public class RankedSteamGameDto {
    private final int rank;
    private final SteamGameDto game;

    public static RankedSteamGameDto fromProto(CStoreTopSellers_GetWeeklyTopSellers_Response_TopSellersRank res) {
        return RankedSteamGameDto.builder()
                .rank(res.getRank())
                .game(SteamGameDto.fromProto(res.getItem()))
                .build();
    }
}
