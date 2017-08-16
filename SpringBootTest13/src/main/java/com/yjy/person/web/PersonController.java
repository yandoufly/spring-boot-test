package com.yjy.person.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.person.entity.PersonEntity;
import com.yjy.person.service.PersonService;
import com.yjy.person.web.vo.PersonVO;


@RestController
@RequestMapping(value = "person")
public class PersonController {

	@Autowired
	private PersonService personService;
	
//	@RequestMapping("/findAll")
//	public List<PersonVO> findAll() {
//		return personService.findAllPersons();
//	}
	@RequestMapping("/findAll")
	public List<PersonEntity> findAll(PersonVO personVO) {
		personVO.setAge(13);
		personVO.setName("xwfweg");
		return personService.doQueryList(personVO);
	}
	
}
