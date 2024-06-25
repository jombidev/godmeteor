package dev.jombi.godmeteor.steam.dto.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Language {
    KR(4, "koreana"), EN(0, "english"),
    ;
    private final int steamLocalizationIndex;
    private final String langId;
}
