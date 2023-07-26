<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>User Details : ${sessionScope.user_dtls}</h4>
	<!-- Encoding of the URL -->
	<%
	 String url=response.encodeURL("logout.jsp");
	%>
	<h5>
	<!-- if cookies are enables we will get URL as logout -->
		<a href="<%= url%>">Log Me Out</a>
	</h5>
</body>
</html>