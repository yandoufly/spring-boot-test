package com.yjy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yjy.entity.Person;

public interface PersonRepository extends JpaRepository<Person, String> {

}
