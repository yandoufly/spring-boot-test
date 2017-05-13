package com.yjy.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping(value = "/hello")
	public String sayHello(@RequestParam String name) {
		return "hello, " + name + " is test AOP do log";
	}
}
