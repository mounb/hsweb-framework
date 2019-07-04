package com.demo.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(Object user) {
        System.out.println("Sender : " + user);
       // Message message = new Message(user.getBytes(),new MessageProperties());
        this.amqpTemplate.convertAndSend("miner", user);
    }
}
