package com.example.cyrptoapp;

import com.example.cyrptoapp.Model.*;
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
    private TradeDataRepository tradeDataRepository;




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
        switch (tradeData.getSymbol()) {
            case "BTCUSDT":
                BTCData btcData = createBTCData(tradeData);
                tradeDataRepository.save(btcData);
                break;
            case "ETHUSDT":
                ETHData ethData = createETHData(tradeData);
                tradeDataRepository.save(ethData);
                break;
            case "SOLUSDT":
                SOLData solData = createSOLData(tradeData);
                tradeDataRepository.save(solData);
                break;
            default:
                // Handle unknown symbol
                break;
        }

    }
    private BTCData createBTCData(TradeData tradeData) {
        BTCData btcData = new BTCData();
        // Set common attributes
        btcData.setSymbol(tradeData.getSymbol());
        btcData.setPrice(tradeData.getPrice());
        btcData.setQuantity(tradeData.getQuantity());
        btcData.setTimestamp(tradeData.getTimestamp());
        btcData.setDate(tradeData.getDate());
        // Set additional attributes specific to BTCData if any
        return btcData;
    }

    private ETHData createETHData(TradeData tradeData) {
        ETHData ethData = new ETHData();
        // Set common attributes
        ethData.setSymbol(tradeData.getSymbol());
        ethData.setPrice(tradeData.getPrice());
        ethData.setQuantity(tradeData.getQuantity());
        ethData.setTimestamp(tradeData.getTimestamp());
        ethData.setDate(tradeData.getDate());
        // Set additional attributes specific to ETHData if any
        return ethData;
    }

    private SOLData createSOLData(TradeData tradeData) {
        SOLData solData = new SOLData();
        // Set common attributes
        solData.setSymbol(tradeData.getSymbol());
        solData.setPrice(tradeData.getPrice());
        solData.setQuantity(tradeData.getQuantity());
        solData.setTimestamp(tradeData.getTimestamp());
        solData.setDate(tradeData.getDate());
        // Set additional attributes specific to SOLData if any
        return solData;
    }
}
