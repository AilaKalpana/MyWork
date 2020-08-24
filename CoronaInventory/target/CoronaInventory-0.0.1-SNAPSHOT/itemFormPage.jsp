<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Item Form</title>
</head>
<body>

	<jsp:include page="header.jsp" />
	
	<h3>${item.icode==null?"New Item":"Edit Item" }</h3>
	
	<form action='${item.icode==null?"addItem":"saveItem" }' method="POST">
		<div>
			<label>icode: </label>
			<input type="number" value="${item.icode }" name="icode" required 
			 ${item.icode==null?"":"readonly" } />
		</div>
		<div>
			
		<label>Product: 
			<select name="title" required>
				<option value="">---SELECT---</option>
				<option value="Mask">Mask</option>
				<option value="Sanitizer.">Sanitizer</option>
				<option value="Cold Tablet">Cold Tablet</option>
				<option value="Cough Syrup">Cough Syrup</option>
			</select>
		</label> <br />
		</div>
		<div>
			<label>Unit: </label>
			<input type="text" value="${item.unit }" name="unit" required />
		</div>
		
		<div>
			<label>Cost Price: </label>
			<input type="decimal" value="${item.costPrice }" name="costPrice" required />
		</div>
<button>SAVE</button>		
	</form>
</body>
</html>