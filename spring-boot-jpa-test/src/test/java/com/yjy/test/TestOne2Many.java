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
	 * 异常：detached entity passed to persist: com.yjy.one2many.domain.Order
	 * 说明：当N端设置了id，而1端不设置id，通过保存1端时会抛出该异常
	 * 解决：1）双方都不设置Id；2）1端也设置id
	 * 方式二中会先去数据库查询有没有该customer记录和order记录，无则插入；因此会发送三条select语句和三条insert语句
	 */
	@Test
	public void testSave3() {
		Order o1 = new Order();
		o1.setId(1);
		o1.setName("o1");
		Order o2 = new Order();
		o2.setId(2);
		o2.setName("o2");
		
		Customer c = new Customer();
		c.setId(1); //一开始先不加
		c.setName("Customer1");
		
		c.getOrders().add(o1);
		c.getOrders().add(o2);
		customerDao.save(c);
	}
	
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
