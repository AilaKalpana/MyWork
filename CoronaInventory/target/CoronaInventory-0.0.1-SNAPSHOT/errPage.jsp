<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset=ISO-8859-1">
<title>Error</title>
</head>
<body>


	<c:if test ="${msg != null}">
			<p><strong>${msg } </strong>
			<a href="Login.jsp">Login Again</a>
		</c:if>
</br>
		
<h3>Error:${errMsg}</h3>
<p>We regret the incovienience,please report it to admin</p>

</body>
</html>