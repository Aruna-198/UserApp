package com.anveshak.repository;

import java.util.List;

import com.anveshak.pojo.User;

public interface UserDao {
	public String addUser(User user);
	public User getUser(String id);
	public String deleteUser(String id);
	public List<User> getAllUser();
	public String updateUser(User user);
	


}
