<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<%--centralized bean creation --%>
<%-- (jsp :) => standard action 
	session.getAttribute("user") : null : session.setAttribute("user",new UserBean()) --%>
<jsp:useBean id="user" class="com.sunbeaminfo.beans.UserBean"
	scope="session" />
<jsp:useBean id="candidate" class="com.sunbeaminfo.beans.CandidateBean"
	scope="session" />
<body>
	<form action="validate.jsp" method="post">
		<table style="background-color: lightgrey; margin: auto">
			<tr>
				<td>Enter User Email</td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td>Enter Password</td>
				<td><input type="password" name="password" /></td>
			</tr>

			<tr>
				<td><input type="submit" value="Login" /></td>
			</tr>
			<tr>
			<td><h4><a href="register.jsp">Register here</a></h4></td>
			</tr>
		</table>
	</form>
	<h4>${sessionScope.user.message}</h4>
</body>
</html>