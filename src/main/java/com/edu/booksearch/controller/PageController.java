package com.edu.booksearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping(value = "/book/search")
    public String bookSearch() {
        return "booksearch";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/registration")
    public String registration() {
        return "registration";
    }
}
