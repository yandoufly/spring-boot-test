package com.yjy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yjy.entity.User;

public interface UserMapper {
	@Select("SELECT * FROM users")
	@Results({
		@Result(property = "nickName", column = "nick_name")
	})
	List<User> getAll();
	
	@Select("SELECT * FROM users WHERE id = #{id}")
	@Results({
		@Result(property = "nickName", column = "nick_name")
	})
	User getOne(Integer id);

	@Insert("INSERT INTO users(userName,password,gender, nick_name) VALUES(#{userName}, #{password}, #{gender}, #{nickName})")
	void insert(User user);

	@Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
	void update(User user);

	@Delete("DELETE FROM users WHERE id =#{id}")
	void delete(Integer id);
}
