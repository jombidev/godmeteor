package dev.jombi.godmeteor.steam.extern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.Message;
import dev.jombi.godmeteor.global.exception.CustomException;
import dev.jombi.godmeteor.global.exception.GlobalExceptionCode;
import dev.jombi.godmeteor.protos.*;
import dev.jombi.godmeteor.steam.dto.RankedSteamGameDto;
import dev.jombi.godmeteor.steam.dto.SteamGameDto;
import dev.jombi.godmeteor.steam.dto.consts.Language;
import dev.jombi.godmeteor.steam.dto.internal.SteamAjaxEventJson;
import dev.jombi.godmeteor.steam.dto.internal.SteamAjaxJsonDataJson;
import dev.jombi.godmeteor.steam.dto.internal.SteamCommunityJson;
import dev.jombi.godmeteor.steam.dto.internal.SteamEventJson;
import dev.jombi.godmeteor.steam.exception.SteamExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class SteamFetchService {

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    private static final String API_ENDPOINT = "https://api.steampowered.com";

    public Map<String, List<SteamGameDto>> feedOnMonth(int year, int month, Language language) {
        var reqMonth = Month.of(month).name().toLowerCase(Locale.ROOT);

        String parse = processString("https://store.steampowered.com/charts/topnewreleases/top_" + reqMonth + "_" + year, Map.of());
        if (parse == null) throw new CustomException(SteamExceptionCode.GET_STEAM_CHART_FAILED);
        var config = Jsoup.parse(parse)
                .body().getElementById("application_config");
        SteamAjaxJsonDataJson data;
        try {
            //noinspection DataFlowIssue
            long clanAccountId = objectMapper.readValue(config.attr("data-community"), SteamCommunityJson.class).clanAccountId();
            String announcementGid = objectMapper.readValue(config.attr("data-event"), SteamEventJson.class).announcementGid();
            String jsonData = processJson(
                    "https://store.steampowered.com/events/ajaxgetpartnerevent",
                    Map.of("clan_accountid", String.valueOf(clanAccountId), "announcement_gid", announcementGid),
                    SteamAjaxEventJson.class
            ).event().jsondata();
            data = objectMapper.readValue(jsonData, SteamAjaxJsonDataJson.class);
        } catch (Exception e) {
            throw new CustomException(SteamExceptionCode.STEAM_CHART_PARSE_FAILED);
        }

        Map<String, List<SteamGameDto>> map = new HashMap<>();

        for (SteamAjaxJsonDataJson.SaleSections section : data.sections()) {
            if (section.capsules().isEmpty()) continue;
            var builder = CStoreBrowse_GetItems_Request.newBuilder();
            for (SteamAjaxJsonDataJson.SaleSections.IdAndType capsule : section.capsules()) {
                builder.addIds(StoreItemID.newBuilder().setAppid(capsule.id()));
            }
            var built = builder
                    .setDataRequest(
                            StoreBrowseItemDataRequest.newBuilder()
                                    .setIncludeAssets(true)
                                    .setIncludeRelease(true)
                                    .setIncludePlatforms(true)
                                    .setIncludeTagCount(20)
                                    .setIncludeReviews(true)
                                    .setIncludeBasicInfo(true)
                                    .setIncludeRatings(true)
                    )
                    .setContext(
                            builder.getContextBuilder()
                                    .setCountryCode("KR")
                                    .setLanguage(language.getLangId())
                                    .setSteamRealm(1)
                    )
                    .build();
            try {
                String s = protobuf(built);

                var m = process(
                        "/IStoreBrowseService/GetItems/v1",
                        Map.of("input_protobuf_encoded", s)
                );

                var parsed = CStoreBrowse_GetItems_Response.parseFrom(m);
                map.put(section.labels().get(language.getSteamLocalizationIndex()), parsed.getStoreItemsList().stream().map(SteamGameDto::fromProto).toList());
            } catch (IOException e) {
                throw new CustomException(SteamExceptionCode.GAME_INFO_FETCH_FAILED);
            }
        }

        return map;
    }

    public List<RankedSteamGameDto> revenue(Language lang) {
        try {
            var builder = CStoreTopSellers_GetWeeklyTopSellers_Request.newBuilder();
            CStoreTopSellers_GetWeeklyTopSellers_Request request = builder.setDataRequest(
                            builder.getDataRequestBuilder()
                                    .setIncludeAssets(true)
                                    .setIncludeRelease(true)
                                    .setIncludePlatforms(true)
                                    .setIncludeTagCount(20)
                                    .setIncludeReviews(true)
                                    .setIncludeBasicInfo(true)
                                    .setIncludeRatings(true)
                    )
                    .setCountryCode("KR")
                    .setContext(
                            builder.getContextBuilder()
                                    .setCountryCode("KR")
                                    .setLanguage(lang.getLangId())
                                    .setSteamRealm(1)
                    )
                    .setPageCount(20)
                    .build();

            String finalRes = protobuf(request);

            var n = process(
                    "/IStoreTopSellersService/GetWeeklyTopSellers/v1",
                    Map.of("input_protobuf_encoded", finalRes)
            );
            var res = CStoreTopSellers_GetWeeklyTopSellers_Response.parseFrom(n);
            return res.getRanksList().stream().map(RankedSteamGameDto::fromProto).toList();
        } catch (Exception e) {
            log.error("Error on fetch: ", e);
            throw new CustomException(GlobalExceptionCode.INTERNAL_SERVER_ERROR);
        }
    }

    public SteamGameDto gameInfo(int id, Language language) {
        var built = CStoreBrowse_GetItems_Request.newBuilder()
                .addIds(StoreItemID.newBuilder().setAppid(id))
                .setDataRequest(
                        StoreBrowseItemDataRequest.newBuilder()
                                .setIncludeAssets(true)
                                .setIncludeRelease(true)
                                .setIncludePlatforms(true)
                                .setIncludeTagCount(20)
                                .setIncludeReviews(true)
                                .setIncludeBasicInfo(true)
                                .setIncludeRatings(true)
                )
                .setContext(
                        StoreBrowseContext.newBuilder()
                                .setCountryCode("KR")
                                .setLanguage(language.getLangId())
                                .setSteamRealm(1)
                )
                .build();

        try {
            var m = process(
                    "/IStoreBrowseService/GetItems/v1",
                    Map.of("input_protobuf_encoded", protobuf(built))
            );


            var parsed = CStoreBrowse_GetItems_Response.parseFrom(m);
            return SteamGameDto.fromProto(parsed.getStoreItems(0));
        } catch (IOException e) {
            log.error("error while thing", e);
            throw new CustomException(SteamExceptionCode.GAME_INFO_FETCH_FAILED);
        }
    }

    @SuppressWarnings("SameParameterValue")
    @SneakyThrows
    private <T> T processJson(String url, Map<String, String> param, Class<T> clazz) {
        return httpClient.send(
                HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(url + "?" + mapParam(param)))
                        .build(),
                new JsonHandler<>(clazz)
        ).body().get();
    }

    @SneakyThrows
    private String processString(String url, Map<String, String> param) {
        return httpClient.send(
                HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(url + "?" + mapParam(param)))
                        .build(),
                HttpResponse.BodyHandlers.ofString()
        ).body();
    }

    @SneakyThrows
    private byte[] process(String path, Map<String, String> param) {
        return httpClient.send(
                HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(API_ENDPOINT + path + "?" + mapParam(param)))
                        .build(),
                HttpResponse.BodyHandlers.ofByteArray()
        ).body();
    }

    private String mapParam(Map<String, String> param) {
        return param.entrySet().stream().map(it -> it.getKey() + "=" + URLEncoder.encode(it.getValue(), StandardCharsets.UTF_8)).collect(Collectors.joining("&"));
    }

    private String protobuf(Message request) throws IOException {
        var baos = new ByteArrayOutputStream(request.getSerializedSize());
        request.writeTo(baos);
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}
