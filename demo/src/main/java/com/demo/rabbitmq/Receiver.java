package com.demo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = {"miner"})
@Component
public class Receiver {

    @RabbitHandler
    public void handler(String message) {
/*        String messageBody = new String(message.getBody());*/
        System.out.println("receiveing :" + message);
    }
}
