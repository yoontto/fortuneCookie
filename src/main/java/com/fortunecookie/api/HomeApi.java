package com.fortunecookie.api;

import com.fortunecookie.dto.GeminiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class HomeApi {

    @GetMapping("/api/data")
    public String test(Map<String, String> params) {
        // google API 키 값 넣어주기
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        params.put("key", "AIzaSyAS4Q4hnWm0sStYMNxe0wDcekYOq0yQHDQ");
        map.setAll(params);

        // WebClient 기본 객체 생성
        WebClient webClient = WebClient.builder()
                .baseUrl("https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent")
                .defaultHeaders(httpHeaders -> httpHeaders
                        .add(HttpHeaders.CONTENT_TYPE, "application/json"))
                .build();

        Mono<GeminiResponse> geminiResponseMono = webClient.post()
                                    .uri(uriBuilder -> uriBuilder
                                            .queryParam("key", "AIzaSyAS4Q4hnWm0sStYMNxe0wDcekYOq0yQHDQ")
                                            .build())
                                    .retrieve()
                                    .bodyToMono(GeminiResponse.class);
        geminiResponseMono.subscribe();


        return geminiResponseMono.toString();
    }
}
