<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Cart</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<h3>Cart Items</h3>
	<form action="/CoronaInventory/checkout" method="post"> 
	<c:choose>
		<c:when test="${cartItems==null || cartItems.isEmpty() }">
			<p>Your Cart is empty, add items to cart</p>
		</c:when>
		<c:otherwise>
			<c:set var="total" value="${0}"/>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>Item Id</th>
					<th>Title</th>
					<th>Unit</th>
					<th>Cost Price</th>
					<th>Action</th>
				</tr>
				<c:forEach items="${cartItems }" var="item">
				<c:set var="total" value="${total + item.costPrice}" />
				<tr>
					<td>${item.icode }</td>
					<td>${item.title }</td>
					<td>${item.quantity }</td>
					<td align="right">${item.costPrice }</td>
					
					<td>
						<a href="deleteCartItem?icode=${item.icode }">DELETE</a> <span>|</span>
						<a href="editCartItem?icode=${item.icode }">EDIT</a> <span>|</span>
						<a href="addCart?icode=${item.icode }&quantity=1">ADD</a>
					</td>
				</tr>			
				</c:forEach>
				
			</table>
			<table>
				<tr>
					<td>&nbsp</td>
					<td>Total</td>
					<td>&nbsp</td>
					<td align="right">${total }</td>
					<td>&nbsp</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
	<br/>
	<button>Order</button>
	</form>
</body>
</html>