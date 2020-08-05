<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<style type="text/css">
form {
	align-content: center;
	margin-top: 2cm;
	margin-left: 12cm;
	background-color: gray;
	color: white;
	height: 10cm;
	width: 9cm;
	padding: 1cm;
}
</style>

</head>
<body>
	<form action="adduser" method="post">
Enter id 
		<input type="number" name=uid placeholder="user id"><br><br>
Enter First Name
		 <input type="text" name="fname" placeholder="first name"><br><br> 
Enter Last Name
	 <input type="text" name="lname" placeholder="last name"><br><br>
 Enter Date Of Birth 
	 <input type="date" name="text" placeholder="dd/mm/yyyy"><br><br>
	 Gender:
	 	<input type="radio" name="gender" value="female">female
				<input type="radio" name="gender" value="other">other<br><br>
 	
		        <input type="submit" value="submit"> 
</form>


</body>
</html>