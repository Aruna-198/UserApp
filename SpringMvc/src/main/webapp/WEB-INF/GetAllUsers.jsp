<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<h2>Customer Manager</h2>
		<table border="1" cellpadding="5">
			<tr>
				<th>UserID</th>
				<th>first-Name</th>
				<th>Last-Name</th>
				<th>Gender</th>
				<th>Date Of Birth</th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.firstName}</td>
					<td>${user.LastName}</td>
					<td>${user.gender}</td>
					<td>${user.dob}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>