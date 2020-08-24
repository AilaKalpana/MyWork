<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items List</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<h3>Items</h3>
	
	<c:choose>
		<c:when test="${items==null || items.isEmpty() }">
			<p>No Items Found</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>icode</th>
					<th>Title</th>
					<th>Unit</th>
					<th>Cost Price</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${items }" var="item">
					<tr>
					<td>${item.icode }</td>
					<td>${item.title }</td>
					<td>${item.unit }</td>
					<td>${item.costPrice }</td>
					
					<td>
						<c:if test ="${role == 'admin'}">
						<a href="deleteItem?icode=${item.icode }">DELETE</a> <span>|</span>
						<a href="editItem?icode=${item.icode }">EDIT</a> <span>|</span>
						<a href="addItem?icode=${item.icode }">ADD</a>
						</c:if>
						<c:if test ="${role == 'user'}">
						<a href="addCart?icode=${item.icode }&quantity=1">ADD</a>
						</c:if>
						
					</td>
				</tr>				
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>