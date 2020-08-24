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
		
			<h2>Signup Details</h2> 
			
			<form action="/CoronaInventory/addUser" method="post"> 
			<br/>First Name:<input type="text" name="firstname" required>
			<br/>Last Name:<input type="text" name="firstname" required> 
			<br/>Email:<input type="text" name="email" required> 
			<br/>Address:<input type="text" name="deliveryaddress" required>
			<br/>
			<br/>Username:<input type="text" name="username" required> 
			<br/>Password:<input type="password" name="password" required> 
			<br/>
			<br/>
			<button>SIGN UP</button>	
			</form> 
	
</body>
</html>