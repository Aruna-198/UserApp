package com.anveshak.repository;

import java.util.List;

import com.anveshak.pojo.User;

public interface UserDao {
	public String addUser(User user);
	public List<User> getAllUser();
	public String updateUser(User user);
	String deleteUser(String email);
	public User getUser(String email);
	


}
