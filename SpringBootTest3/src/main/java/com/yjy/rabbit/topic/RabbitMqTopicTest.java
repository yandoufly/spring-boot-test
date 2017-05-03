package com.yjy.rabbit.topic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjy.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTopicTest {

	@Autowired
	private TopicSender topicSender;

	@Test
	public void hello() throws Exception {
		topicSender.send();
		System.out.println("-------------");
		topicSender.send1();
		System.out.println("-------------");
		topicSender.send2();
	}
}
