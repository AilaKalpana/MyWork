<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
	<head>	
		<title>Inventory - Home</title>
	</head>
	<body>
		<jsp:include page="MainHeader.jsp" />
		
		
		
		<c:if test ="${msg != null}">
			<p><strong>${msg } </strong>
		</c:if>

	</body>
</html>