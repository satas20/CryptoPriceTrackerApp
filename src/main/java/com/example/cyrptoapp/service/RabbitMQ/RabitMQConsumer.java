package com.example.cyrptoapp.service.RabbitMQ;

import com.example.cyrptoapp.entity.TradeData;
import com.example.cyrptoapp.service.TradeDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabitMQConsumer {
    @Autowired
    private final TradeDataService tradeDataService;

    public RabitMQConsumer(TradeDataService tradeDataService) {
        this.tradeDataService = tradeDataService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consumeMessage(TradeData message) {
        Logger LOGGER = LoggerFactory.getLogger(RabitMQConsumer.class);

        LOGGER.info("Message received: " + message.getSymbol() +" "+ message.getPrice() +" "+ message.getQuantity()  );
        tradeDataService.processTradeData(message);
    }
}
