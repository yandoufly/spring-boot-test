package com.yjy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.domain.Pruduct;
import com.yjy.service.PruductService;

@RestController
public class PruductController {

	@Autowired
	private PruductService pruductService;

	@RequestMapping("/getPrud")
//	@Cacheable("prudCache")
	@Cacheable(value="getPrud",key="'getPrud'.concat(#root.args[0])")  
	public Pruduct getPrud(@RequestParam(required = true) String id) {
		System.out.println("如果第二次没有走到这里说明缓存被添加了");
		return pruductService.getPrud(Integer.parseInt(id));
	}

	@RequestMapping("/savePrud")
	@CachePut(value = "prudsaveCache", key = "#result.id +''")
	public Pruduct savePrud(Pruduct prud) {
		return prud;
	}
}
