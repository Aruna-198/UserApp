package com.anveshak.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.anveshak.model.User;

public interface UserMapper {
	
	@Insert("INSERT INTO table_User(FIRSTNAME,LASTNAME,EMAIL,PASSWORD,MOBILENUMBER,DOB,GENDER) VALUES(#{firstName},#{lastName},#{email}, #{password},#{mobileNumber},#{dob},#{gender})")
	public int addUser(User user);
	
	@Select("SELECT*FROM table_User")
	public List<User> getAllUser();
	
	@Update("UPDATE table_User SET FIRSTNAME=#{firstName},LASTNAME=#{lastName},EMAIL=#{email},PASSWORD=#{password},MOBILENUMBER=#{mobileNumber},Gender=#{gender},DOB=#{dob} where EMAIL=#{email}")
	public int updateUser(User user);
	
	@Delete("DELETE FROM table_User WHERE EMAIL=#{email}")
	public int deleteUser(String email);
	
	@Select("SELECT*FROM table_User WHERE EMAIL=#{email}")
	public User getUser(String email);
	

}
