package com.yjy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yjy.domain.User;

public interface UserDao extends JpaRepository<User, Integer>{

	User findByUsername(String username);

}
