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

	@Override
	public String addUser(User user) {
		ValidateUser errorMessage = new ValidateUser();
		String msg = "";
		User existingUser = userMapper.getUser(user.getEmail());
		msg = errorMessage.checkError(user);
		if (!(msg==null))
			return msg;
		if (!(existingUser == null)) {
			msg = "user already exist";
			return msg;
		} else
			userMapper.addUser(user);
		return msg;
	}

	@Override
	public String deleteUser(String email) {
		String message = "";
		int count = userMapper.deleteUser(email);
		if (count == 1)
			message = "succesfully ";
		else
			message = "fail";
		return message;
	}

	@Override
	public String updateUser(User user) {
		ValidateUser errorMessage = new ValidateUser();
		String msg = "";
		msg = errorMessage.checkError(user);
		if (!(msg==null))
			return msg;
		else
			userMapper.updateUser(user);
		return msg;
	}

	@Override
	public List<User> getAllUser() {
		return userMapper.getAllUser();
	}

	@Override
	public User getUser(String email) {
		User user = userMapper.getUser(email);
		return user;

	}

}
