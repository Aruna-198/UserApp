package com.anveshak.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.anveshak.model.User;
import com.anveshak.service.UserService;
import com.anveshak.util.UserRegistrationMessage;
import com.anveshak.util.ValidateUser;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/home")
	public String home() {
		return "HomePage";
	}
	@RequestMapping("/delete")
	public String delete() {
		return "DeleteForm";
	}
	@RequestMapping("/update")
	public String update() {
		return "UpdateForm";
	}
	@RequestMapping("/showForm")
	public String showForm(Model model) {
		User user = new User();
		model.addAttribute(user);
		return "AddUser";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("user") User user, ValidateUser errorMessage, Model model) {
		String message = "";
		String status = "";
		ModelAndView mv = null;
		boolean flag=false;
		String msg = errorMessage.checkError(user);
		if(!(msg=="")) {flag=true;}
		if (flag) {
			model.addAttribute("flag",flag);
			model.addAttribute("error", msg);
			return new ModelAndView("AddUser");
		} else
			status = userService.addUser(user);
		if (status.equals("success")) {
			message = "User Add Succesfuly";
			mv = new ModelAndView("status", "message", message);
		}
		if (status.equals("fail")) {
			message = "User Insertion Fails";
			mv = new ModelAndView("status", "message", message);
		}
		if (status.equals("exist")) {
			message = "User Already Existed";
			mv = new ModelAndView("status", "message", message);
		}
		return mv;
	}
	@RequestMapping(value = "/updateUser{email}", method = RequestMethod.POST)
	public ModelAndView updateUser(@RequestParam("email") String email, Model model) {
		ModelAndView mv = null;
		User user = userService.getUser(email);
		if (user == null) {
			String message = "user not exist";
			mv = new ModelAndView("status", "message", message);
		} else {
			model.addAttribute(user);
			mv = new ModelAndView("EditForm");
		}
		return mv;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editForm(@ModelAttribute("user") User user, ValidateUser errorMessage, Model model) {
		String status = "";
		ModelAndView mv = null;
		String msg = errorMessage.checkError(user);
		if (!(msg == "")) {
			model.addAttribute("error", msg);
			return new ModelAndView("EditForm");
		} else
			status = userService.updateUser(user);
		if (status.equalsIgnoreCase("fail")) {
			mv = new ModelAndView("status", "message", "updation fails");
		}
		if (status.equalsIgnoreCase("success")) {
			mv = new ModelAndView("status", "message", "Updated succesfully");
		}
		return mv;
	}
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = null;
		String email = request.getParameter("email");
		String status = userService.deleteUser(email);
		if (status.equals("Deleted")) {
			String msg = "user delete succesfuly";
			mv = new ModelAndView("status", "message", msg);
		}
		if (status.equals("fail")) {
			String msg = "deletion fails";
			mv = new ModelAndView("status", "message", msg);
		}
		if (status.equals("NotExisted")) {
			String msg = "user not existed";
			mv = new ModelAndView("status", "message", msg);
		}
		return mv;
	}
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public ModelAndView getUser(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		userService.getUser(email);
		return new ModelAndView("UserInfo");
	}
	@RequestMapping("/allUsers")
	public String getAllUsers(Model model) {
		model.addAttribute("users", userService.getAllUser());
		return "GetAllUsers";
	}

}
