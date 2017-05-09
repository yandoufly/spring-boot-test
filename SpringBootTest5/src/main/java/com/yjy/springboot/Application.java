package com.yjy.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.yjy.springboot.mapper*")
@SpringBootApplication
public class Application {

	/**
	 * <p>
	 * 测试 RUN
	 * <br>
	 * 1、http://localhost:8080/user/test1
	 * 2、http://localhost:8080/user/test2
	 * </p>
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
