package com.anveshak.service;

import java.util.List;

import com.anveshak.pojo.User;
import com.anveshak.repository.UserDao;
import com.anveshak.repository.UserDaoImpl;

public class UserServiceImpl implements UserService{
	UserDao userdao;

	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}

	@Override
	public String addUser(String id, String fname, String lname, String gender, String birthDate) {
		User user=new User();
		user.setUid(id);
		user.setFirstName(fname);
		user.setLastName(lname);
		user.setGender(gender);
		user.setDob(birthDate);
		String status=userdao.addUser(user);
		return status;
	}
	@Override
	public String updateUser(String id, String fname, String lname, String gender, String birthDate) {
		User user=new User();
		user.setUid(id);
		user.setFirstName(fname);
		user.setLastName(lname);
		user.setGender(gender);
		user.setDob(birthDate);
		String status=userdao.updateUser(user);
		return status;
	}

	@Override
	public User getUser(String id) {
		return userdao.getUser(id);
		
	}

	@Override
	public String deleteUser(String id) {
		return userdao.deleteUser(id);
	}

	@Override
	public List<User> getAllUser() {
		return userdao.getAllUser();
	}
	

	

}
