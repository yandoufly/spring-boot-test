package com.yjy.rabbit.manyTomany;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FirstSender {
	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(int i) {
		String context = "spirng boot neo queue"+" ****** "+i;
		System.out.println("FirstSender : " + i + context);
		this.rabbitTemplate.convertAndSend("yjy", context);
	}
}
