package com.yjy.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.yjy.springboot.entity.User;
import com.yjy.springboot.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	/**
	 * 增删改查 CRUD
	 */
	@RequestMapping("/test1")
	public User test1() {
		userService.insertSelective(new User("张三", 17, 1));
		return userService.selectById(1L);
	}

	/**
	 * 插入 OR 修改
	 */
	@RequestMapping("/test2")
	public User test2() {
		userService.insert(new User("王五", 19, 3));
		return userService.selectById(1L);
	}

	/**
	 * 分页 PAGE
	 */
	@RequestMapping("/test3")
	public Page<User> test3() {
		return userService.selectPage(new Page<User>(0, 12), null);
	}

}
