package com.yjy.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.entity.Person;
import com.yjy.service.PersonService;

@RestController
public class PersonController {

	private final static Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;

	/*查询所有记录 (分页)*/
	@RequestMapping(value = "/personsJson")
	public Page<Person> getPersonJson(HttpServletRequest request) {
		logger.info("查询所有记录(条件与分页)");

		// 拿到当前页
		String pageIndexTemp = request.getParameter("pageIndex");
		int pageIndex = 0;
		if (pageIndexTemp != null && !"".equals(pageIndexTemp)) {
			pageIndex = Integer.parseInt(pageIndexTemp);
		}

		String identityNumber = request.getParameter("identityNumber");
		String personName = request.getParameter("personName");
		String gender = request.getParameter("gender");
		String birthYear = request.getParameter("birthYear");

		Person person = new Person();
		if (identityNumber != null && !"".equals(identityNumber)) {
			person.setIdentityNumber(identityNumber);
		}
		if (personName != null && !"".equals(personName)) {
			person.setPersonName(personName);
		}
		if (gender != null && !"".equals(gender)) {
			person.setGender(gender);
		}
		// if (birthYear != null && !"".equals(birthYear)) {
		// Date birthTime = new Date(Integer.parseInt(birthYear), 0, 1);
		// person.setBirthTime(birthTime);
		// }

		return personService.findAll(pageIndex, person);
	}

	// 查看用户
	@RequestMapping(value = "/findPerson")
	public Person addOrEditperson(String id) {
		return personService.findOne(id);
	}

	// 删除用户
	@RequestMapping(value = "/delPerson")
	public String deletePerson(String id) {
		try {
			personService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("删除失败");
			return "删除失败";
		}
		logger.info("删除成功，删除用户id：" + id);

		return "删除成功";
	}

	// 添加或编辑用户
	@RequestMapping(value = "/addOrEditperson")
	public String addOrEditperson(Person person) {
		System.out.println(person);
		try {
			personService.save(person);
		} catch (Exception e) {
			e.printStackTrace();
			return "操作失败";
		}
		return "操作成功";
	}
}
