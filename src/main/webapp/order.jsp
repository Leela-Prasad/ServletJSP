<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<jsp:include page="header.jsp" />
	<h1>Ricky's Restaurant</h1>
	<h2>Order your food</h2>
	
	<form action='orderReceived.html' method='POST' >
		<ul>
			<c:forEach items="${menuItems}" var="menuItem" >
				<li>
					${menuItem} <input type='text' name='item_${menuItem.id}' />
				</li>
			</c:forEach>
		</ul>
		<input type='submit' />
	</form>
	<jsp:include page="footer.jsp" />
</body>
</html>