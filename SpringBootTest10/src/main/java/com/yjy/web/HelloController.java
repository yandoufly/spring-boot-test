package com.yjy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	@GetMapping(value = "/")
	public String index(){
		return "index";
	}
	
	@GetMapping(value = "/hello")
	public String sayHello() {
		return "hello";
	}
	
	@RequestMapping("/login")
    public String login() {
        return "login";
    }
}
