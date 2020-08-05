package com.anveshak.service;

import java.util.List;

import com.anveshak.pojo.User;

public interface UserService {
	public String addUser(String id,String fname, String lname, String gender, String birthDate);
	public String updateUser(String id,String fname, String lname, String gender, String birthDate);
	public User getUser(String id);
	public String deleteUser(String id);
	public List<User> getAllUser();
	

}
