package com.yjy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjy.domain.User;
import com.yjy.repository.UserDao;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	
}
