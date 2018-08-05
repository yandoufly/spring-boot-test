package com.yjy.one2many.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjy.one2many.dao.CustomerDao;
import com.yjy.one2many.domain.Customer;
import com.yjy.one2many.domain.Order;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	//1端和N端：新建态，插入sql
	@Transactional
	public void testSave() {
		Customer customer = new Customer();
		customer.setName("customer1");
		
		Order order1 = new Order();
		order1.setName("order1");
		order1.setCustomer(customer);

		Order order2 = new Order();
		order2.setName("order2");
//		order2.setCustomer(customer);
		
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);
		
		customerDao.save(customer);
	}
	
	//1端：持久态、N端：持久态和新建态，更新和插入sql
	@Transactional
	public void testSave2(Integer id) {
		Customer customer = customerDao.findOne(id);
		Order order4 = new Order();
		order4.setName("order4");
		order4.setCustomer(customer);
		
		List<Order> orders = customer.getOrders();
		for (Order order : orders) {
			order.setName(order.getName()+"11");
		}
		orders.add(order4);
		
		customerDao.save(customer);
	}
	
	//级联删除sql
	@Transactional
	public void testDelete(Integer id) {
		customerDao.delete(id);
	}
}
