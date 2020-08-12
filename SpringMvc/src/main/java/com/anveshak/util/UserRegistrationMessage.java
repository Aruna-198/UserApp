package com.anveshak.util;

import com.anveshak.pojo.User;

public class UserRegistrationMessage  {
	public String hasError(User user) throws CustomValidation {
		String message="";
	
		
		if(user.getFirstName()=="") {
			message="first name required";
			throw new CustomValidation(message);
		   
		    }
		if (user.getLastName()== "") { 
			message="last name required";
			throw new CustomValidation(message);
		}
		if (user.getEmail()== "") { 		
			message="email required";
			throw new CustomValidation(message);
		}
		if (user.getPassword()=="" && user.getPassword().length() < 6) {
			message="password must not be null and lenght must be 6 or more";
			throw new CustomValidation(message);
		}
	    if(user.getEmail()==""&& (!user.getEmail().matches(" /^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$/"))) { 
	    	message="email required";
			throw new CustomValidation(message);
	    }
		if (!user.getMobileNumber().matches("[7-9]{1}[0-9]{9}")) {
			message=" invalid mobile number";
			throw new CustomValidation(message);
		}
		
	
		return message;
	}

}
