package com.anveshak.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.anveshak.pojo.User;
import com.anveshak.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/home")
	public String home(){
			return "HomePage";
	}
	@RequestMapping("/delete")
	public String delete(){
			return "DeleteForm";
	}
	@RequestMapping("/update")
	public String update(){
			return "UpdateForm";
	}
	@RequestMapping("/showform")
	public String showForm(Model model) {
		User user=new User();
		model.addAttribute(user);
		return "AddUser";
		}

	@RequestMapping(value="/save",method = RequestMethod.POST)
	 public ModelAndView addUser( @Valid User user,BindingResult errors )  {
		ModelAndView mv=null;
		if(errors.hasErrors()) {
			mv=new ModelAndView("AddUser","user",user);
		}else {
	String status = userService.addUser(user);
	if(status.equalsIgnoreCase("success")) {
		String message="Registrtion successful";
		mv=new ModelAndView("status","message",message);
	}
	if(status.equalsIgnoreCase("fails")) {
		String message="Registrtion unsuccessful";
		mv=new ModelAndView("status","message",message);
	}}
	return mv;
	}
	
	@RequestMapping(value= "/updateuser{email}" ,method = RequestMethod.POST)
	public ModelAndView updateUser(@RequestParam("email")String email,  Model model) {
		ModelAndView mv=null;
		User user=userService.getUser(email);
		model.addAttribute(user);
		if(user==null) {
			String message="user not exist";
			mv=new ModelAndView("status","message",message);
		}
		else {
			mv=new ModelAndView("EditForm");
		}
		return mv;
}
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView editForm(@ModelAttribute("user")User user) {
		String status=userService.updateUser(user);
		ModelAndView mv=null;
		if(status.equalsIgnoreCase("fail")) {
			mv=new ModelAndView("status","message","updation fails");
		}
		if(status.equalsIgnoreCase("success")) {
			mv=new ModelAndView("status","message","Updated succesfully");
		}
		
		return mv;
	}
	@RequestMapping(value = "/deleteuser" ,method = RequestMethod.POST)
	public ModelAndView deleteUser(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = null;
		String email=request.getParameter("email");
		String status = userService.deleteUser(email);
		if (status.equals("Deleted")) {
			String msg="user delete succesfuly";
			mv = new ModelAndView("status", "message",msg );
		}
		if (status.equals("fail")) {
			String msg="deletion fails";
			mv = new ModelAndView("status", "message", msg );
		}
		if (status.equals("NotExisted")) {
			String msg="user not existed";
			mv = new ModelAndView("status", "message", msg);
		}
		return mv;	

	}
	@RequestMapping(value ="/getuser" ,method = RequestMethod.GET)	
	public ModelAndView getUser(HttpServletRequest request, HttpServletResponse response){
		String email=request.getParameter("email");
		User user=userService.getUser(email);
		return new ModelAndView("UserInfo");
		
	}
	
	@RequestMapping("/allusers")
		public String getAllUsers(Model model ) {
		
		model.addAttribute("users",userService.getAllUser());
		return "GetAllUsers";
		}
	
	
}
