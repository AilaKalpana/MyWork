<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login</title>
</head>


	<body> 
	<% String username=request.getParameter("username"); 
	String password=request.getParameter("password"); 
	if((username.equals("admin") && password.equals("admin"))) { 
		session.setAttribute("username",username); 
		response.sendRedirect("header.jsp"); 
		} else 
			response.sendRedirect("errPage.jsp"); %> 
	</body>

</html>