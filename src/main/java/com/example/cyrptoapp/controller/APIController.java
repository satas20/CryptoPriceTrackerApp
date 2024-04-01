package com.example.cyrptoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class APIController {

    @GetMapping("/currentPrice")
    public String getCurrentPrice() {
        String currPrice = (10 + 10) + "";
        return currPrice;
    }
}
