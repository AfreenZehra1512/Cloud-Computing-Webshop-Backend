package com.webshop.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ping")
public class Ping_Controller {
    @GetMapping
    public String pingRequest() {
        return "Ping Response";
    }
}