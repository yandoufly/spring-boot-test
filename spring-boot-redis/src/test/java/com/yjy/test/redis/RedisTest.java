package com.yjy.test.redis;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yjy.Application;
import com.yjy.domain.Pruduct;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {
	@Autowired
	private RedisConnectionFactory factory;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	public void testRedisCF() {
		// 得到一个连接
		RedisConnection conn = factory.getConnection();
		conn.set("hello".getBytes(), "world".getBytes());
		System.out.println(new String(conn.get("hello".getBytes())));
	}

	@Test
	public void testRedisTemplate() {
		redisTemplate.opsForValue().set("key1", "value1");
		System.out.println(redisTemplate.opsForValue().get("key1"));
	}

	@Test
	public void testRedisTemplateList() {
		Pruduct prud = new Pruduct(1, "洗发水", "100ml");
		Pruduct prud2 = new Pruduct(2, "洗面奶", "200ml");
		// 依次从尾部添加元素
		redisTemplate.opsForList().rightPush("pruduct", prud);
		redisTemplate.opsForList().rightPush("pruduct", prud2);
		// 查询索引0到商品总数-1索引（也就是查出所有的商品）
		List<Object> prodList = redisTemplate.opsForList().range("pruduct", 0,
				redisTemplate.opsForList().size("pruduct") - 1);
		for (Object obj : prodList) {
			System.out.println((Pruduct) obj);
		}
		System.out.println("产品数量:" + redisTemplate.opsForList().size("pruduct"));
	}
}
