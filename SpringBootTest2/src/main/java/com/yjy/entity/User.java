package com.yjy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "t_user") 
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "username", length = 32) 
	@NotBlank(message = "这字段必传")
	private String username;
	
	@Column(name = "age") 
	@Min(value = 18, message = "未成年人禁止入门")
	private Integer age;
	
	@Column(name = "password", length = 32) 
	@NotNull(message = "密码不能为空")
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", age=" + age + ", password=" + password + "]";
	}
}
