package com.anveshak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.anveshak.pojo.User;
import com.anveshak.repository.UserDao;
import com.anveshak.repository.UserDaoImpl;

public class UserServiceImpl implements UserService{

	private UserDao userdao;
	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}

	@Override
	public List<User> getAllUser() {
		return userdao.getAllUser();
	}

	@Override
	public User getUser(String email) {
		return userdao.getUser(email);
		
	}

	@Override
	public String deleteUser(String email) {
		String status=userdao.deleteUser(email);
		return status;
	}
	@Override
	public User addUser(User user) {
		User newUser=userdao.addUser(user);
		return newUser;
		
		
	}

	@Override
	public String updateUser(User user) {
		return userdao.updateUser(user);
	}
	

	

}
