package com.yjy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yjy.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
     User findByName(String username);
}
