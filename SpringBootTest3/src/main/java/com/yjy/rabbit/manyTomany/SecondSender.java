package com.yjy.rabbit.manyTomany;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecondSender {
	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(int i) {
		String context = "spirng boot neo queue"+" ****** "+i;
		System.out.println("SecondSender : " + i + context);
		this.rabbitTemplate.convertAndSend("yjy", context);
	}
}
