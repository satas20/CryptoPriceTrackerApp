package com.example.cyrptoapp;

import com.binance.connector.client.WebSocketStreamClient;
import com.binance.connector.client.impl.WebSocketStreamClientImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BinanceWebSocketClient {
    @Value("${binance.api.key}")
    private String apiKey;

    @Value("${binance.api.secret}")
    private String secretKey;

    @Autowired
    private TradeDataService tradeDataService;



    @PostConstruct
    public void connect() {

        WebSocketStreamClient wsStreamClient = new WebSocketStreamClientImpl();


        int streamID1 = wsStreamClient.aggTradeStream("btcusdt",((message) -> {
            TradeData tradeData = tradeDataService.parseTradeData(message);
            tradeDataService.processTradeData(tradeData);

            System.out.println("Message Received: " + message);

        }));

    }
}
