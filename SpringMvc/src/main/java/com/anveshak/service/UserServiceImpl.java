package com.anveshak.service;

import java.util.List;

import com.anveshak.model.User;
import com.anveshak.repository.UserDao;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	public void setUserdao(UserDao userdao) {
		this.userDao = userdao;
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public User getUser(String email) {
		return userDao.getUser(email);

	}

	@Override
	public String deleteUser(String email) {
		String status = userDao.deleteUser(email);
		return status;
	}

	@Override
	public String addUser(User user) {
		String status = userDao.addUser(user);
		return status;

	}

	@Override
	public String updateUser(User user) {
		return userDao.updateUser(user);
	}

}
