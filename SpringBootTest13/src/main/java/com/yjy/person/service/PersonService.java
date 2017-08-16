package com.yjy.person.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.yjy.person.entity.PersonEntity;
import com.yjy.person.repository.PersonRepository;
import com.yjy.person.web.vo.PersonVO;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	public List<PersonEntity> doQueryList(PersonVO personVO) {
		List<PersonEntity> findAll = personRepository.findAll(new Specification<PersonEntity>() {
			@Override
			public Predicate toPredicate(Root<PersonEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//		        Predicate p1 = cb.like(root.get("name").as(String.class), personVO.getName());
//		        Predicate p2 = cb.equal(root.get("dr").as(Integer.class), personVO.getDr());
//		        Predicate p3 = cb.equal(root.get("age").as(Integer.class), personVO.getAge());
//				return cb.and(p3, cb.or(p1, p2));
				
		        Predicate p2 = cb.equal(root.get("dr").as(Integer.class), personVO.getDr());
		        return cb.and(p2);
			}
		});
		return findAll;
	}
	
	public List<PersonVO> findAllPersons(){
		List<PersonVO> listVO = new ArrayList<>();
		List<PersonEntity> listEntity = personRepository.findAllPersons();
		for(PersonEntity entity : listEntity) {
			PersonVO vo = new PersonVO();
			BeanUtils.copyProperties(entity, vo);
			listVO.add(vo);
		}
		return listVO;
	}
	
	
	public long count() {
		return personRepository.count(new Specification<PersonEntity>() {
			@Override
			public Predicate toPredicate(Root<PersonEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		        return cb.equal(root.get("dr").as(Integer.class), 0);
			}
		});
	}
	
	public PersonVO findOnePerson(String id){
		PersonVO vo = new PersonVO();
		PersonEntity entity = personRepository.findOneById(id);
		BeanUtils.copyProperties(entity, vo);
		return vo;
	}
	
	public void deleteOnePerson(String id) {
		personRepository.delete(id);
	}
	
	public PersonVO save(PersonVO vo){
		PersonEntity entity = new PersonEntity();
		BeanUtils.copyProperties(vo, entity);
		personRepository.save(entity);
		BeanUtils.copyProperties(entity, vo);
		return vo;
	}

	public void deletePersons(List<String> ids) {
		personRepository.deletePersons(ids);
	}
}
