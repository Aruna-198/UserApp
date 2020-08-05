<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="updateuser" method="post">
<h4>user id:</h4>
		<input type="hidden" name=id value="${uid}"><br><br>
Enter First Name
		 <input type="text" name="fname" placeholder="first name" value="${fname}"><br><br> 
Enter Last Name
	 <input type="text" name="lname" placeholder="last name" value="${lname}"><br><br>
 Enter Date Of Birth 
	 <input type="date" name="date" placeholder="DOB" value="${dob}"><br><br>
Gender
	<input type="radio" name="gender" placeholder="gender" value="${gender}"> 
		<input type="reset" value="Reset" onclick="AddUserForm">      	<input type="submit" value="Update"> 
	
	</form>

		
</body>
</html>