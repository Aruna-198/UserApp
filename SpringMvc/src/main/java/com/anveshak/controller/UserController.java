package com.anveshak.controller;

import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.anveshak.pojo.User;
import com.anveshak.service.UserService;

public class UserController extends MultiActionController {
	private UserService userService;
	String status = "";
	String message = "";
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 ModelAndView mv=new ModelAndView("HomePage");
			return mv;
	}
	public ModelAndView updateuser(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		String id = request.getParameter("id");
	
		String firstName=request.getParameter("fname");
		String lastName=request.getParameter("lname");
		String gender=request.getParameter("gender");
		String date=request.getParameter("dob");
		
		status = userService.updateUser(id, firstName, lastName, gender, date);
		if (status.equals("success")) {
			message = "User Add succesfuly";
		}
		if (status.equals("fail")) {
			message = "User insertion fails";
		}

		return new ModelAndView("status", "message", message);
}
	public ModelAndView adduser(HttpServletRequest request, HttpServletResponse response) throws ParseException {
			String id = request.getParameter("id");
		
			String firstName=request.getParameter("fname");
			String lastName=request.getParameter("lname");
			String gender=request.getParameter("gender");
			String date=request.getParameter("dob");
			
			status = userService.addUser(id, firstName, lastName, gender, date);
			if (status.equals("success")) {
				message = "User Add succesfuly";
			}
			if (status.equals("fail")) {
				message = "User insertion fails";
			}

			return new ModelAndView("status", "message", message);
}

	public ModelAndView editform(HttpServletRequest request,HttpServletRequest response) {
		String id=request.getParameter("id");
		User user=userService.getUser(id);
		ModelAndView mv=null;
		if(user==null) {
			mv=new ModelAndView("status","message","user not existed");
		}
		else {
			mv=new ModelAndView("UserEdit","uid",id);
		}
		
		return mv;
	}
	public ModelAndView deleteuser(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = null;
		String id = request.getParameter("uid");
		status = userService.deleteUser(id);
		if (status.equals("success")) {
			mv = new ModelAndView("status", "message", "user delete succesfuly");
		}
		if (status.equals("fail")) {
			mv = new ModelAndView("status", "message", "deletion fails");
		}
		if (status.equals("NotExised")) {
			mv = new ModelAndView("status", "message", "user not existed");
		}

		return null;

	}
		public ModelAndView getAllUsers(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("GetAllUsers");
		}
	
	

}
