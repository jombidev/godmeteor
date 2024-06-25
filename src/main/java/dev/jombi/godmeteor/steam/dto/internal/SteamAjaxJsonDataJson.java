package dev.jombi.godmeteor.steam.dto.internal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SteamAjaxJsonDataJson(@JsonProperty("sale_sections") List<SaleSections> sections) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record SaleSections(List<IdAndType> capsules, @JsonProperty("localized_label") List<String> labels) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public record IdAndType(int id, String type) {}
    }
}
