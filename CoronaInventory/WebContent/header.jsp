<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<header>

	<h1> Corona Management Portal</h1>
	<hr/>
		<nav>
			<a href = "index.jsp">Home</a>
			<span></span>
			<a href="list">Items List</a>
			<span></span>
			<c:if test ="${role == 'admin'}">
			<a href = "newItem">Add New</a>
			</c:if>
			<c:if test ="${role == 'user'}">
			<a href = "viewCart">View Cart</a>
			</c:if>
			 <a href="logout.jsp">Logout</a>
			
		</nav>
		<hr />
</header>