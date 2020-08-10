package com.anveshak.service;

import java.util.List;

import com.anveshak.pojo.User;

public interface UserService {
	public User getUser(String email);
	public List<User> getAllUser();
	public String addUser(User user);
	public String updateUser(User user);
	public String deleteUser(String email);
	

}
