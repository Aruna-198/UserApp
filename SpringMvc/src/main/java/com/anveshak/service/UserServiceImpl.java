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
	public String addUser(User user) {
		String message="";
		User existingUser=userDao.getUser(user.getEmail());
		if(!(existingUser==null)) {
		message="Exist";
		}else {
		int count=userDao.addUser(user);
		if(count==1) {
			 message="success";
		}
		else {
			message="fail";
		}}
		return message;
		
	}
	@Override
	public String deleteUser(String email) {
		String message="";
		int count = userDao.deleteUser(email);
		if(count==1) {
			 message="success";
		}
		else {
			message="NotExisted";
		}
		return message;
	}
	@Override
	public String updateUser(User user) {
		String message="";
		int count = userDao.updateUser(user);
		if(count==1) {
			 message="success";
		}
		else {
			message="NotExisted";
		}
		return message;
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public User getUser(String email) {
		return userDao.getUser(email);

	}
	

}
