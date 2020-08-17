package com.anveshak.repository;

import java.util.List;

import com.anveshak.model.User;

public interface UserDao {
	public int addUser(User user);
	public List<User> getAllUser();
	public int updateUser(User user);
	public int deleteUser(String email);
	public User getUser(String email);
	


}
