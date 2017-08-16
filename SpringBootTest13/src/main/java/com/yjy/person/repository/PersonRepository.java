package com.yjy.person.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.yjy.core.repository.BaseRepository;
import com.yjy.person.entity.PersonEntity;

public interface PersonRepository extends BaseRepository<PersonEntity> {
	@Query(value = "select * from person where dr = 0", nativeQuery = true)
	List<PersonEntity> findAllPersons();
	
	@Query(value = "select * from person where dr = 0 and id = ?1", nativeQuery = true)
	PersonEntity findOneById(String id);
	
	@Transactional
	@Modifying
	@Query(value = "update PersonEntity set dr = 1 where id in (?1)")
	int deletePersons(List<String> ids);
}
