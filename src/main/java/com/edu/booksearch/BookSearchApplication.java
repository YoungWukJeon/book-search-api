package com.edu.booksearch;

import com.edu.booksearch.external.KakaoBookSearchApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"com.edu.booksearch.util", "com.edu.booksearch.service"})
public class BookSearchApplication {

	@Value("${book-search.kakao-api.url}")
	String kakaoApiUrl;

//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}

	@Bean
	public KakaoBookSearchApiClient kakaoBookSearchApiClient() {
		return new KakaoBookSearchApiClient(new RestTemplate(), kakaoApiUrl);
	}

	public static void main(String[] args) {
		SpringApplication.run(BookSearchApplication.class, args);
	}

}
