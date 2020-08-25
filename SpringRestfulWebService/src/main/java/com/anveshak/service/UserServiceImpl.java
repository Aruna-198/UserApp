package com.anveshak.service;
import java.util.List;

import com.anveshak.model.User;
import com.anveshak.repository.UserMapper;
import com.anveshak.util.ValidateUser;

public class UserServiceImpl implements UserService {
private UserMapper userMapper;
	
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public String addUser(User user) {
		ValidateUser errorMessage = new ValidateUser();
		String msg = "";
		int count;
		User existingUser = userMapper.getUser(user.getEmail());
		msg = errorMessage.checkError(user);
		if (!(msg == null))
			return msg;
		if (!(existingUser == null)) {
			msg = "exist";
			return msg;
		} else
			count = userMapper.addUser(user);
		if (count == 1)
			return "user add successfuly";
		else
			return "operation fails";
	}

	public String deleteUser(String email) {
		String message = "";
		User existingUser = userMapper.getUser(email);
		if (existingUser == null) 
			return "NotExist";
		else	
			userMapper.deleteUser(email);
		return message;
	}

	public List<User> getAllUser() {
		return userMapper.getAllUser();
	}

	public User getUser(String email) {
		User user = userMapper.getUser(email);
		return user;
	}

	
	public User updateUser(String email, User user) {
		
		User existingUser= userMapper.getUser(email);
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		existingUser.setDob(user.getDob());
		existingUser.setGender(user.getGender());
		userMapper.updateUser(existingUser);
		return existingUser;
		
		
	}

}
