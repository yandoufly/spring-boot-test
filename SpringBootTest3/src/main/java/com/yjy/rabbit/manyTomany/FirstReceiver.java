package com.yjy.rabbit.manyTomany;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "yjy")
public class FirstReceiver {
	@RabbitHandler
    public void process(String hello) {
        System.out.println("FirstReceiver  : " + hello);
    }
}
