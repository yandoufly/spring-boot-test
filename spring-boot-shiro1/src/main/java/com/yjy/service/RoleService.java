package com.yjy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjy.repository.RoleDao;

@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
}
