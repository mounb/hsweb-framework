package com.demo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@RabbitListener(queues = {"miner"})
@Component
public class Receiver {

    @RabbitHandler
    public void process(Map<String,Object> message) {
/*        String messageBody = new String(message.getBody());*/
        System.out.println("receiveing :" + message);
    }
}
