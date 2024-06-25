package dev.jombi.godmeteor.steam.dto.internal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SteamAjaxEventJson(SteamAjaxEventObject event) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record SteamAjaxEventObject(String jsondata) {}
}
