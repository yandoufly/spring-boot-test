package com.yjy.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yjy.Application;
import com.yjy.one2many.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestOne2Many {
	
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 生成的数据：
	 * insert into t_customer (name) values (?) 
	 * insert into t_order (customer_id, name) values (?, ?) 1,order1
	 * insert into t_order (customer_id, name) values (?, ?) null,order2
	 */
	@Test
	public void testSave() {
		customerService.testSave();
	}
	
	/**
	 * 生成的数据:
	 * insert into t_order (customer_id, name) values (?, ?)
	 * update t_order set customer_id=?, name=? where id=?
	 * update t_order set customer_id=?, name=? where id=?
	 */
	@Test
	public void testSave2() {
		customerService.testSave2(new Integer(5));
	}
	
	/**
	 * 删除一条t_customer记录和关联的3条t_order记录
	 */
	@Test
	public void testDelete() {
		customerService.testDelete(new Integer(5));
	}
}
