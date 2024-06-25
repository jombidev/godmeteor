package dev.jombi.godmeteor.pcroom;

import dev.jombi.godmeteor.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pcroom")
public class PcRoomController {
    private final PcRoomGetLank pcRoomGetLank;

    @GetMapping(value = "/rank")
    public ResponseEntity<ResponseData<List<PcRoomJson.GameRank>>> PcRoomGetGameRankResponseEntity() {
        return ResponseData.ok("게임 조회 완료", pcRoomGetLank.get());
    }

}
