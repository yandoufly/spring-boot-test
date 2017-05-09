package com.yjy.springboot.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.yjy.springboot.entity.User;
import com.yjy.springboot.mapper.UserMapper;
import com.yjy.springboot.service.IUserService;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User> implements IUserService {

}