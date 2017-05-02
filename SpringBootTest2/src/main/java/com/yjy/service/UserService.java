package com.yjy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yjy.entity.User;
import com.yjy.enums.ResultEnum;
import com.yjy.exception.MyException;
import com.yjy.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	//测试事务
	@Transactional
	public void insertTwo() {
		User user1 = new User();
		user1.setUsername("test AA");
		user1.setAge(22);
		user1.setPassword("123");
		userRepository.save(user1);
		
		User user2 = new User();
		user2.setUsername("test BB");
		user2.setAge(23);
		//故意不写密码
		userRepository.save(user2);
		
	}
	
	public Integer getAge(Integer id) throws Exception {
		User user = userRepository.findOne(id);
		Integer age = user.getAge();
		if(age < 10) {
			//返回"你还在上小学吧" code=100
            throw new MyException(ResultEnum.PRIMARY_SCHOOL);
		}else if (age > 10 && age < 16) {
            //返回"你可能在上初中" code=101
            throw new MyException(ResultEnum.MIDDLE_SCHOOL);
        }
		// > 16岁 ...
		return age;
	}
	
	/*通过Id查询一个女生的信息*/
    public User findOne(Integer id) {
        return userRepository.findOne(id);
    }
    /*查询所有记录*/
	public List<User> findAll() {
		return userRepository.findAll();
	}
    /*添加/更改一条记录*/
	public User save(User user) {
		return userRepository.save(user);
	}
	/*删除一条记录*/
	public void delete(Integer id) {
		userRepository.delete(id);
	}
	/*通过age查询符合条件的记录*/
	public List<User> findByAge(Integer age) {
		return userRepository.findByAge(age);
	}
}
