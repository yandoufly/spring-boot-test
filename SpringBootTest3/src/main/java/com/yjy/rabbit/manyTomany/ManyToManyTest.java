package com.yjy.rabbit.manyTomany;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjy.rabbit.hello.HelloSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyToManyTest {

	@Autowired
	private FirstSender firstSender;
	@Autowired
	private SecondSender secondSender;

	@Test
	public void hello() throws Exception {
		for (int i=0;i<100;i++){
			firstSender.send(i);
			secondSender.send(i);
		}
	}
}
