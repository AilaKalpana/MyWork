<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

	<h1>Login</h1> 
			<c:if test ="${msg != null}">
			<p><strong>${msg } </strong>
		</c:if>
			<form action="/CoronaInventory/login" method="post"> 
			<br/>Username:<input type="text" name="username" required> 
			<br/>Password:<input type="password" name="password" required> 
			<button>LOGIN</button>	
			
			<br/>
			<br/>
			<a href="NewUser.jsp">New User Register here</a>
			<span></span>
			</form> 
			

</body>
</html>