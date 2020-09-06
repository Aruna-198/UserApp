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
		String msg = "";
		User existingUser = userMapper.getUser(user.getEmail());
		if (!(existingUser == null)) {
			msg = "User already exist";
			return msg;
		} else
			userMapper.addUser(user);
		return null;
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
		String msg = "";
			userMapper.updateUser(user);
		return null;
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
