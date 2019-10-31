package com.edu.booksearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.edu.booksearch.util", "com.edu.booksearch.service", "com.edu.booksearch.external", "com.edu.booksearch.controller"})
public class BookSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSearchApplication.class, args);
	}

}
