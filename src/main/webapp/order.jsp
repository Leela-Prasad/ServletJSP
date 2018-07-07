<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="com.java.ee.domain.MenuItem" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order Page</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<h1>Ricky's Restaurant</h1>
	<h2>Order your food</h2>
	
	<%
		List<MenuItem> menuItems = (List<MenuItem>)request.getAttribute("menuItems");
	%>
	<form action='orderReceived.html' method='POST' >
		<ul>
			<% for(MenuItem menuItem : menuItems) { %>
				
				<li><%=menuItem %> <input type='text' name='item_<%=menuItem.getId() %>' /></li>
			
			<%  } %>
			
		</ul>
		<input type='submit' />
	</form>
	<%@ include file="footer.jsp" %>
</body>
</html>