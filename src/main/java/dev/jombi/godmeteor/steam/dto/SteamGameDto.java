package dev.jombi.godmeteor.steam.dto;

import dev.jombi.godmeteor.protos.StoreItem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SteamGameDto {
    private final int appId;
    private final String name;
    private final String description;
    private final int reviewCount;
    private final String reviewLabel;
    private final int positivePercent;
    private final String rating;

    public static SteamGameDto fromProto(StoreItem response) {
        return SteamGameDto.builder()
                .appId(response.getAppid())
                .name(response.getName())
                .description(response.getBasicInfo().getShortDescription())
                .reviewCount(response.getReviews().getSummaryFiltered().getReviewCount())
                .reviewLabel(response.getReviews().getSummaryFiltered().getReviewScoreLabel())
                .positivePercent(response.getReviews().getSummaryFiltered().getPercentPositive())
                .rating(response.hasGameRating() ? response.getGameRating().getType() + " " + response.getGameRating().getRating() : "N/A")
                .build();
    }
}
