package com.edu.booksearch;

import com.edu.booksearch.external.KakaoBookSearchApiClient;
import com.edu.booksearch.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"com.edu.booksearch.util", "com.edu.booksearch.service", "com.edu.booksearch.external"})
public class BookSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSearchApplication.class, args);
	}

}
