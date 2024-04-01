package com.example.cyrptoapp.RabbitMQ;

import com.example.cyrptoapp.Model.TradeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

public class RabitMQProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private static final Logger LOGGER= LoggerFactory.getLogger(RabitMQProducer.class);
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(TradeData message) {
        LOGGER.info("Sending message: " + message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);


    }
}
