<%@page import="pojos.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>from 1st page....</h4>
	<%
	//create product instance n add it under suitable scope
	Product product = new Product(Integer.parseInt(request.getParameter("pid")), request.getParameter("name"),
			Double.parseDouble(request.getParameter("price")));
	request.setAttribute("product_dtls", product);
	%>
	<jsp:forward page="test5.jsp">
		<jsp:param value="food_items" name="category" />
	</jsp:forward>
</body>
</html>