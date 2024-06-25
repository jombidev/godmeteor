package dev.jombi.godmeteor.steam.controller;

import dev.jombi.godmeteor.global.response.ResponseData;
import dev.jombi.godmeteor.steam.dto.RankedSteamGameDto;
import dev.jombi.godmeteor.steam.dto.SteamGameDto;
import dev.jombi.godmeteor.steam.dto.consts.Language;
import dev.jombi.godmeteor.steam.extern.SteamFetchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/steam")
@RequiredArgsConstructor
public class SteamController {
    private final SteamFetchService fetcher;

    @GetMapping("/revenue")
    public ResponseEntity<ResponseData<List<RankedSteamGameDto>>> test() {
        return ResponseData.ok("최고 인기 게임 조회 완료", fetcher.revenue(Language.KR));
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<ResponseData<SteamGameDto>> gameInfo(@PathVariable int id) {
        return ResponseData.ok("게임 조회 완료", fetcher.gameInfo(id, Language.KR));
    }

    @GetMapping("/feed")
    public ResponseEntity<ResponseData<Map<String, List<SteamGameDto>>>> topGames(@RequestParam(defaultValue = "2024") int year, @RequestParam int month) {
        return ResponseData.ok(year + "년 " + month + "월의 피드 조회 성공", fetcher.feedOnMonth(year, month, Language.KR));
    }
}
