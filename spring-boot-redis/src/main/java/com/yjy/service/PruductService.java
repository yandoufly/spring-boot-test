package com.yjy.service;

import org.springframework.stereotype.Service;

import com.yjy.domain.Pruduct;

@Service
public class PruductService {

	public Pruduct getPrud(int id) {
		if(id == 1) {
			return new Pruduct(id, "AAA", "CCC");
		}
		return null;
	}
	
}
