package com.anveshak.util;

import com.anveshak.model.User;

public class ValidateUser {

	public String checkError(User user){
		String message="";

		if(user.getFirstName()=="") {
			message="first name required";
			return message;
			
		    }
		if (user.getLastName()== "") { 
			message="last name required";
			return message;
			}
		if (user.getEmail()== "") { 		
			message="email required";
			return message;
			}
		if (user.getPassword()=="" && user.getPassword().length() < 6) {
			message="password must not be null and lenght must be 6 or more";
			return message;
			}
	    if(user.getEmail()==""&& (!user.getEmail().matches(" /^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$/"))) { 
	    	message="email required";
	    	return message;
			}
		if (!user.getMobileNumber().matches("[7-9]{1}[0-9]{9}")) {
			message=" invalid mobile number";
			return message;
		}
		if (user.getDob()=="") {
			message=" birth date required";
			return message;
		}
		if (user.getGender()==null) {
			message=" please select gender  ";
			return message;		
		}
		return message;
	}

}
