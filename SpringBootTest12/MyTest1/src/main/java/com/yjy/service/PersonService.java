package com.yjy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.yjy.entity.Person;
import com.yjy.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	/*查询所有记录(分页)*/
	public Page<Person> findAll(int pageIndex, Person person) {

		// 创建匹配器，即如何使用查询条件
		Example<Person> example = null;
		if (person != null) {
			example = Example.of(person);
		}

		// 分页
		Sort sort = new Sort(Direction.DESC, "birthTime");
		int size = 10;
		Pageable pageable = new PageRequest(pageIndex, size, sort);

		// 判断是否输入有条件
		if (example == null) {
			return personRepository.findAll(pageable);
		}
		return personRepository.findAll(example, pageable);
	}

	/*添加/更改一条记录*/
	public Person save(Person person) {
		return personRepository.save(person);
	}

	/*删除一条记录*/
	public void delete(String id) {
		try {
			personRepository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Person findOne(String id) {
		return personRepository.findOne(id);
	}

}
