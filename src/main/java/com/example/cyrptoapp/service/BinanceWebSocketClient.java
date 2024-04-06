package com.example.cyrptoapp.service;

import com.binance.connector.client.WebSocketStreamClient;
import com.binance.connector.client.impl.WebSocketStreamClientImpl;
import com.example.cyrptoapp.entity.TradeData;
import com.example.cyrptoapp.service.RabbitMQ.RabitMQProducer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BinanceWebSocketClient {


    @Autowired
    private TradeDataService tradeDataService;

    @Autowired
    private RabitMQProducer rabitMQProducer;


    @PostConstruct
    public void connect() {

        WebSocketStreamClient wsStreamClient = new WebSocketStreamClientImpl();



        int streamID1 = wsStreamClient.aggTradeStream("btcusdt",((message) -> {
            TradeData tradeData = tradeDataService.parseTradeData(message);

            //System.out.println("Message Received: " + message);
            rabitMQProducer.sendMessage(tradeData );
        }));

        int streamID2 = wsStreamClient.aggTradeStream("ethusdt",((message) -> {
            TradeData tradeData = tradeDataService.parseTradeData(message);

            //System.out.println("Message Received: " + message);
            rabitMQProducer.sendMessage(tradeData );
        }));

        int stramID3 = wsStreamClient.aggTradeStream("solusdt",((message) -> {
            TradeData tradeData = tradeDataService.parseTradeData(message);

            //System.out.println("Message Received: " + message);
            rabitMQProducer.sendMessage(tradeData );
        }));
    }
}
