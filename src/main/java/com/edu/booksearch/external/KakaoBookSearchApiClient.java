package com.edu.booksearch.external;

import com.edu.booksearch.model.BookInfoDto;
import com.edu.booksearch.model.KakaoApiResponseDto;
import com.fasterxml.jackson.core.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class KakaoBookSearchApiClient {
    private RestTemplate restTemplate;
    private String url;

    public KakaoBookSearchApiClient(RestTemplate restTemplate, String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public URI getUri(String query, int page) {
//        ClientHttpRequest request = null;
//        request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
//        request.getHeaders().set("Authorization", "KakaoAK 856b481599a0d44105d666e865d3fe65");
//        request.getBody().write(requestBody.getBytes());
        String uriTemplate = this.url + "?query={query}&page={page}";
        return UriComponentsBuilder.fromUriString(uriTemplate).build(query, page);
    }

    public KakaoApiResponseDto findByQueryWithPage(String query, int page) {
        RequestEntity<Void> requestEntity = RequestEntity.get(getUri(query, page))
                .header("Authorization", "KakaoAK 856b481599a0d44105d666e865d3fe65")
                .build();

//        ResponseEntity<BookInfoDto> response = restTemplate.exchange(requestEntity, BookInfoDto.class);
        return restTemplate.exchange(requestEntity, KakaoApiResponseDto.class).getBody();
    }
}
