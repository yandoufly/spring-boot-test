package com.yjy.rabbit.fanout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjy.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqFanoutTest {

	@Autowired
	private FanoutSender fanoutSender;

	@Test
	public void hello() throws Exception {
		fanoutSender.send();
	}
}
