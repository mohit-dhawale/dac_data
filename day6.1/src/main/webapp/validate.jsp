<%@page import="pojos.User"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%!//JSP declaration block
	HashMap<String, User> users;

	public void jspInit() {
		System.out.println("in init");
		users = new HashMap<>();//size=0, initCapa=16, L.F=0.75
		//populate the map with sample data
		users.put("rama@gmail.com", new User("rama@gmail.com", "1234", 34));
		users.put("shubham@gmail.com", new User("shubham@gmail.com", "2234", 38));
	}%>
<body>
	<%
	String email = request.getParameter("em");
	String pwd = request.getParameter("pass");
	//get user details from the map
	User user = users.get(email);
	if (user != null) {
		//=> email valid , chk pwd
		// multiple request from same user use session
		if (user.getPassword().equals(pwd)) {
			//login success ! , add user dtls under session scope
			session.setAttribute("user_dtls", user);
			//URL rewriting + redirect 
			//if cookies are disables ,location : details.jsp;JSESSIONID=fd6snv4459wv]
			response.sendRedirect(response.encodeRedirectURL("details.jsp"));
			//encodeURL from Client pull 1
			//
		} else {
	%>
	<h5 style="color: red;">
		Invalid Password , <a href="login2.jsp">Retry</a>
	</h5>
	<%
	}
	%>
	<%
	} else {
	%>
	<h5 style="color: red;">
		New User , <a href="register.jsp">SignUp</a>
	</h5>
	<%
	}
	%>
</body>
</html>