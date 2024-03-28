package com.example.cyrptoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class TradeDataService {
    @Autowired
    private TradeDataRepository repository;

    public TradeData parseTradeData(String payload) {
        // Assuming you're using Jackson for JSON parsing
        ObjectMapper mapper = new ObjectMapper();

        // Let's assume your documentation identified the following fields
        String SYMBOL_FIELD = "s";
        String PRICE_FIELD = "p";
        String QUANTITY_FIELD = "q";
        String TIMESTAMP_FIELD = "E";

        try {
            JsonNode rootNode = mapper.readTree(payload);
            TradeData tradeData = new TradeData();
            tradeData.setSymbol(rootNode.path("s").asText());

            // Convert price and quantity from string to BigDecimal
            tradeData.setPrice(new BigDecimal(rootNode.path("p").asText()));
            tradeData.setQuantity(new BigDecimal(rootNode.path("q").asText()));

            tradeData.setTimestamp(rootNode.path("E").asLong());
            tradeData.setDate(new Date(tradeData.getTimestamp()));
            return tradeData;
        } catch (IOException e) {
            throw new RuntimeException("Error parsing trade data", e);
        }

    }
    public void processTradeData(TradeData tradeData) {
        // Potentially add data to a queue or process before saving
        repository.save(tradeData);
    }
}
