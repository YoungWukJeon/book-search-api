package com.edu.booksearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.edu.booksearch.util", "com.edu.booksearch.service"})
@SpringBootApplication
public class BookSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSearchApplication.class, args);
	}

}
