package com.yjy.controller;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.entity.Result;
import com.yjy.entity.User;
import com.yjy.service.UserService;
import com.yjy.utils.ResultUtil;

@RestController
public class UserController {
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/*查询所有记录*/
	@GetMapping(value = "/users")
	public List<User> getUsers(){
		logger.info("userList");
		return userService.findAll();
	}
	
	/*查询一条记录*/
	@GetMapping(value = "/users/{id}")
	public User findOne(@PathVariable("id") Integer id) {
		return userService.findOne(id);
	}
	
	/*添加一条记录*/
	@PostMapping(value = "/users")
	public Result<User> addUser(@Valid User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
		}
		
		return ResultUtil.success(userService.save(user));
	}
	
	/*更新一条数据*/
	@PutMapping(value = "/users/{id}")
	public User updateUser(@PathVariable("id") Integer id,
			@RequestParam("username") String username,
			@RequestParam("age") Integer age,
			@RequestParam("password") String password) {
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setAge(age);
		user.setPassword(password);
		
		return userService.save(user);
	}
	
	//删除
	@DeleteMapping(value = "users/{id}")
	public void deleteUser(@PathVariable("id") Integer id) {
		userService.delete(id);
	}
	
	//通过年龄查询女生列表
	@GetMapping(value = "users/age/{age}")
	public List<User> findByAge(@PathVariable("age") Integer age) {
		return userService.findByAge(age);
	}
	
	//事务管理测试
	@PostMapping(value = "/users/two")
    public void girlTwo() {
        userService.insertTwo();
    }
    
    //通过age查询数据
    @GetMapping(value = "users/getAge/{id}")
    public Integer getAge(@PathVariable("id") Integer id) throws Exception{
        return userService.getAge(id);
    }
}
