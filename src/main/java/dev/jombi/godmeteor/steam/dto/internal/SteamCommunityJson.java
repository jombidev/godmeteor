package dev.jombi.godmeteor.steam.dto.internal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SteamCommunityJson(
    @JsonProperty("CLANACCOUNTID") long clanAccountId
) {}
