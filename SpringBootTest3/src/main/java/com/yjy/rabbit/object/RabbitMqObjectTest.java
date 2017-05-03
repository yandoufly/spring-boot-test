package com.yjy.rabbit.object;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjy.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqObjectTest {

	@Autowired
	private ObjectSender objectSender;

	@Test
	public void hello() throws Exception {
		User user = new User();
		user.setName("Test AA");
		user.setPass("123");
		objectSender.send(user );
	}
}
