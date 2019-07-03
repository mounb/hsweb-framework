package com.demo.rabbitmq;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Setter
@Getter
@ToString
@Component
public class Bean implements Serializable {

    private String id;

    private String content;
}
