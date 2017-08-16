package com.yjy.person.web.vo;

import com.yjy.core.vo.SuperVO;

public class PersonVO extends SuperVO{

	private static final long serialVersionUID = -4745138914786180462L;
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
