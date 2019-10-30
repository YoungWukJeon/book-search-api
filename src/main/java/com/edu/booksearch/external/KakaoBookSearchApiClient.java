package com.edu.booksearch.external;

import com.edu.booksearch.model.search.kakao.KakaoApiResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;

@Component
public class KakaoBookSearchApiClient {

    @Value("${book-search.kakao-api.url}")
    private String kakaoApiUrl;

    private RestTemplate restTemplate;

    @PostConstruct
    public void init() {
        this.restTemplate = new RestTemplate();
    }

    private URI getUri(String query, int page) {
        String uriTemplate = this.kakaoApiUrl + "?query={query}&page={page}";
        return UriComponentsBuilder.fromUriString(uriTemplate).build(query, page);
    }

    public KakaoApiResponseDto findByQueryWithPage(String query, int page) {
        RequestEntity<Void> requestEntity = RequestEntity.get(getUri(query, page))
                .header("Authorization", "KakaoAK 856b481599a0d44105d666e865d3fe65")
                .build();

        return restTemplate.exchange(requestEntity, KakaoApiResponseDto.class).getBody();
    }
}
