package com.edu.booksearch;

import com.edu.booksearch.external.KakaoBookSearchApiClient;
import com.edu.booksearch.util.DateTimeUtil;
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

	@Bean
	public KakaoBookSearchApiClient kakaoBookSearchApiClient() {
		return new KakaoBookSearchApiClient(new RestTemplate(), kakaoApiUrl);
	}

	@Bean
	public DateTimeUtil dateTimeUtil() {
		return new DateTimeUtil();
	}

	public static void main(String[] args) {
		SpringApplication.run(BookSearchApplication.class, args);
	}

}
