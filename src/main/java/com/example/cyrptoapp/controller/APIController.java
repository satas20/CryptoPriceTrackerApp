package com.example.cyrptoapp.controller;

import com.example.cyrptoapp.service.TradeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class APIController {
    @Autowired
    private final TradeDataService tradeDataService;

    public APIController(TradeDataService tradeDataService) {
        this.tradeDataService = tradeDataService;
    }

    @GetMapping("/currPriceBTC")
    public ResponseEntity<String> getCurrentPriceBTC() {
        return ResponseEntity.ok().body(tradeDataService.getCurrentprice("BTCUSDT"));
    }

    @GetMapping("/currPriceETH")
    public ResponseEntity<String> getCurrentPriceETH() {
        return ResponseEntity.ok().body(tradeDataService.getCurrentprice("ETHUSDT"));
    }

    @GetMapping("/currPriceSOL")
    public ResponseEntity<String> getCurrentPriceSOL() {
        return ResponseEntity.ok().body(tradeDataService.getCurrentprice("SOLUSDT"));
    }


    @GetMapping("/")
    //I want to return index.html here
    public ResponseEntity<String> getIndex() {
        return ResponseEntity.ok().body("index.html");
    }
}
