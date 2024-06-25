package dev.jombi.godmeteor.steam.dto.internal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SteamEventJson(@JsonProperty("ANNOUNCEMENT_GID") String announcementGid) {}
