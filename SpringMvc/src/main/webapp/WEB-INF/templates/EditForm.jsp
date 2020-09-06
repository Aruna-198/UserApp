<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">
<title>User Update</title>

<style type="text/css">
.container {
  border-radius: 5px;
  background-color:#f2f2f2;
  padding: 20px;
  align-content: center;
  width: 11cm;
  height:11cm;
  margin-left: 10cm;
  margin-top: 20px;
}
label {
	display: inline-block;
	width: 130px;
	margin: 8px;
	text-align: left;
}
input[type=text], input[type=password], input[type=email],input[type=date] {
 width: 200px;
 padding:8px;
  height:10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
  	display: inline-block;
  
}

button {
	margin: 12px;
    background-color: #45a049;;
    width: 3cm;
     background-color: #4CAF50;
 	 color: white;
 	 padding: 12px 20px;
 	 border: none;
 	 border-radius: 4px;	
 	 margin-left: 180px;
 	 
}
input[type=submit]:hover {
  background-color: #45a049;
}

</style>
<script type="text/javascript">
function updateConfirm(){
	var a=confirm("are you want to save this changes??");
	if(a)
	return true;
	else
	return false;
}

</script>
</head>
<body>
<div class="container">
    <h1 align="center"> Update Form</h1>
    <h3 style="color: red; margin-left: 4cm;" th:text="${error}"></h3>
    <form  th:action="@{/edit}"  th:object="${user}" method="post">
    <table>   
     <tr>
        <td><label>First Name:</label></td>
       <td><input type="text" th:field="*{firstName}" /><br/></td>
		 </tr>
		 <tr>
       <td> <label>Last Name:</label></td>
        <td><input type="text" th:field="*{lastName}" /><br/></td>
        </tr>
        <tr>
        <td><label>Email:</label></td>
        <td><input type="email" th:field="*{email}" /><br/></td>
        </tr>
        <tr>
        <td><label>Password:</label></td>
        <td><input type="password" th:field="*{password}" /><br></td>
        </tr>
        <tr>
       <td> <label>MobileNumber:</label></td>
        <td><input type="text"  th:field="*{mobileNumber}" /><br/></td>
         </tr>
         <tr>
        <td><label>Date Of Birth:</label></td>
        <td><input type="date" th:field="*{dob}" /><br/></td>
        </tr>
		 <tr>
        <td><label>Gender:</label>
        <td><input type="radio" th:field="*{gender}" value="Male" />Male</td>
        <td><input type="radio" th:field="*{gender}" value="Female" />Female<br/></td>
         </tr>
 		</table>
        <button type="submit"  onclick="return updateConfirm();">Update</button><br><br>
        <button  style="background-color: dodgerblue;  margin-top: 15px"><a th:href="@{/home}">HOME</a></button>  
        
          
    </form>
</div>
</body>
</html>