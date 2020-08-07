package com.anveshak.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.anveshak.pojo.User;
import com.anveshak.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	String status = "";
	
	@RequestMapping("/home")
	public String home(){
			return "HomePage";
	}
	
	@RequestMapping("/userform")
	public String showForm(Model model){
		User user=new User();
		model.addAttribute(user);
		return "AddUser";
	}
	@RequestMapping("/update")
	public String update(){
				return "UpdateForm";
	}
	@RequestMapping("/delete")
	public String delete(){
				return "DeleteForm";
	}
	@RequestMapping("/search")
	public String search(){
				return "SearchForm";
	}
	@RequestMapping("/getall")
	public String getall(){
				return "AllUsers";}
	
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	 public String adduser(@ModelAttribute("user") User user,Model model)  {
	User newUser = userService.addUser(user);
	model.addAttribute(newUser);
		return "UserInfo";
}
	
	@RequestMapping(value= "/updateuser" ,method = RequestMethod.POST)
	public String updateuser(HttpServletRequest request, HttpServletResponse response) {
		String email=request.getParameter("email");
		User user=userService.getUser(email);
		return "UserEdit";
}
	@RequestMapping(value="/edit", method = RequestMethod.PUT)
	public ModelAndView editform(@ModelAttribute("user")User user) {
		status=userService.updateUser(user);
		ModelAndView mv=null;
		if(user==null) {
			mv=new ModelAndView("status","message","user not existed");
		}
		else {
			mv=new ModelAndView("status","message","Updated succesfully");
		}
		
		return mv;
	}
	@RequestMapping(value = "/deleteuser" ,method = RequestMethod.POST)
	public ModelAndView deleteuser(HttpServletRequest request,HttpServletResponse response) {
		String email=request.getParameter("email");
		ModelAndView mv = null;
				status = userService.deleteUser(email);
		if (status.equals("success")) {
			mv = new ModelAndView("status", "message", "user delete succesfuly");
		}
		if (status.equals("fail")) {
			mv = new ModelAndView("status", "message", "deletion fails");
		}
		if (status.equals("NotExised")) {
			mv = new ModelAndView("status", "message", "user not existed");
		}

		return mv;

	}
	@RequestMapping(value ="/getuser/{email}" ,method = RequestMethod.GET)	
	public ModelAndView getUser(HttpServletRequest request, HttpServletResponse response){
		String email=request.getParameter("email");
		User user=userService.getUser(email);
		return new ModelAndView("UserInfo");
		
	}
	
	@RequestMapping("/allusers")
		public ModelAndView getAllUsers(Model model ) {
		java.util.List<User> users=userService.getAllUser();
		model.addAttribute(users);
		return new ModelAndView("GetAllUsers");
		}
	
	

}
