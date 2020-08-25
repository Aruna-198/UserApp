package com.anveshak.service;
import java.util.List;
import com.anveshak.model.User;

public interface UserService {
	public User getUser(String email);
	public List<User> getAllUser();
	public String deleteUser(String email);
	public String addUser(User user);
	public User updateUser(String email, User user);
	

}