package com.yjy.domain;

import java.io.Serializable;

public class Pruduct implements Serializable {
	private static final long serialVersionUID = -4182059279163234289L;
	private int id;
	private String name;
	private String ccc;
	
	public Pruduct(){
		
	}
	public Pruduct(int id, String name, String ccc) {
		super();
		this.id = id;
		this.name = name;
		this.ccc = ccc;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCcc() {
		return ccc;
	}
	public void setCcc(String ccc) {
		this.ccc = ccc;
	}
}
