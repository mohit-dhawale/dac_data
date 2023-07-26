<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h5>${sessionScope.user.message}</h5>
	<h5>in admin page</h5>
	<%-- hello , admin name --%>
	<h4>Hello , ${sessionScope.user.userDetails.firstName}</h4>
	<!-- in Java Code OGNL 
	session.getAttribute("user").getUserDetails.firstName : send to client -->
	
	<%-- list of top 2 candidates --%>
	<h3>Top 2 Candidates</h3>
	<!-- session.getAttribute("candidate").getCandidateDetails.topCandidates() -->
	<h4>${sessionScope.candidate.topCandidates}</h4>
	
</body>
</html>