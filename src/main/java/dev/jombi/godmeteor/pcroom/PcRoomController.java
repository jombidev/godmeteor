package dev.jombi.godmeteor.pcroom;

import dev.jombi.godmeteor.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class PcRoomController {
    private final PcRoomGetLank pcRoomGetLank;

    @GetMapping(value = "/pcRank")
    public String getGameRank(@RequestParam String gameRank) {
        return pcRoomGetLank.get();
    }

    @GetMapping(value = "/users")
    public ResponseEntity<ResponseData<String>> PcRoomGetGameRankResponseEntity() {
        return ResponseData.ok("잘되나?", pcRoomGetLank.get());
    }


}
