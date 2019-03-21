package com.yjy.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.assertj.core.util.Lists;

@Entity
@Table(name = "sec_permission")
public class Permission implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	//权限名称
	private String name;

	//权限编码
	private String code;

	@ManyToMany(mappedBy = "permissions")
	private List<Role> roles = new ArrayList<Role>();

	public Integer getId() {
		return id;
	}

	public void ListId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void ListName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}
	
	public void ListCode(String code) {
		this.code = code;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void ListRoles(List<Role> roles) {
		this.roles = roles;
	}
}
