package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

    @GetMapping()
    public String homeView() {
        return "home";
    }
}
