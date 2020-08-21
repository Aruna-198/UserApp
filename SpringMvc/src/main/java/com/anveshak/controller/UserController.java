package com.anveshak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping("/showForm")
	public String showForm(Model model) {
		User user = new User();
		model.addAttribute(user);
		return "AddUser";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user, ValidateUser errorMessage, Model model) {
		String status = userService.addUser(user);
		if (!(status == null)) {
			model.addAttribute("error", status);
			return "AddUser";
		}
		return "redirect:allUsers";
	}

	@RequestMapping(value = "/updateUser/{email}", method = RequestMethod.GET)
	public ModelAndView updateUser(@PathVariable(value = "email") String email, Model model) {
		ModelAndView mv = null;
		User user = userService.getUser(email);
		model.addAttribute(user);
		mv = new ModelAndView("EditForm");
		return mv;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editForm(@ModelAttribute("user") User user, Model model) {
		String status = userService.updateUser(user);
		if (!(status == null)) {
			model.addAttribute("error", status);
			return "EditForm";
		}
		return "redirect:allUsers";
	}

	@RequestMapping(value = "/deleteUser/{email}", method = RequestMethod.GET)
	public String delete(@PathVariable("email") String email) {
		userService.deleteUser(email);
		return "redirect:/allUsers";
	}

	@RequestMapping(value = "/getUser/{email}", method = RequestMethod.GET)
	public ModelAndView getUser(@PathVariable("email") String email) {
		userService.getUser(email);
		return new ModelAndView("UserInfo");
	}

	@RequestMapping("/allUsers")
	public String getAllUsers(Model model) {
		model.addAttribute("users", userService.getAllUser());
		return "GetAllUsers";
	}

}
