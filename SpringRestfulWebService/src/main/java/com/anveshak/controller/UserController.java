package com.anveshak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.anveshak.model.User;
import com.anveshak.service.UserService;
import com.anveshak.util.ValidateUser;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Void> addUser(@RequestBody User user) {
		String status = userService.addUser(user);
		if(status.equals("exist"))
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		else
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/delete/{email}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deletUser(@PathVariable("email") String email) {
		String status = userService.deleteUser(email);
		if(status.equals("NotExist"))
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<User>(HttpStatus.OK);
	}

	@RequestMapping(value = "/update/{email}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("email") String email, @RequestBody User user) {
		User currentUser=null;
		User existingUser = userService.getUser(email);
		if (existingUser == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}else
		currentUser=userService.updateUser(email,user);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	@RequestMapping("/getUser/{email}")
	public ResponseEntity<User> getUser(@PathVariable("email") String email) {
		User user= userService.getUser(email);
		if(user==null)
			return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<User>(user,HttpStatus.OK);
	}

	@RequestMapping(value = "allUsers", method = RequestMethod.GET)
	public  ResponseEntity<List<User>> getAllUsers() {
		List<User> users= userService.getAllUser();
		if(users.isEmpty())
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		
	}

}
