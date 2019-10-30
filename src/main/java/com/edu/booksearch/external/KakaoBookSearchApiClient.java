package com.edu.booksearch.external;

import com.edu.booksearch.model.search.kakao.KakaoApiResponseDto;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class KakaoBookSearchApiClient {
    private RestTemplate restTemplate;
    private String url;

    public KakaoBookSearchApiClient(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public URI getUri(String query, int page) {
        String uriTemplate = this.url + "?query={query}&page={page}";
        return UriComponentsBuilder.fromUriString(uriTemplate).build(query, page);
    }

    public KakaoApiResponseDto findByQueryWithPage(String query, int page) {
        RequestEntity<Void> requestEntity = RequestEntity.get(getUri(query, page))
                .header("Authorization", "KakaoAK 856b481599a0d44105d666e865d3fe65")
                .build();

        return restTemplate.exchange(requestEntity, KakaoApiResponseDto.class).getBody();
    }
}
