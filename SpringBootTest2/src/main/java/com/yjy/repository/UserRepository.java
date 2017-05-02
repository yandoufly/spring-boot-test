package com.yjy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yjy.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	//通过年龄来查询
    public List<User> findByAge(Integer age);
}
