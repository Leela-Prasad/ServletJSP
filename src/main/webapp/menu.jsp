<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.java.ee.domain.MenuItem" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<h1>Ricky's Restaurant</h1>
	<h2>Menu</h2>
	<ul>
	 	<%
	 		List<MenuItem> menuItems = (List<MenuItem>)request.getAttribute("menuItems");
		 	for (MenuItem menuItem : menuItems) {
				out.println("<li>" + menuItem + "</li>");
			}
	 	%>
	</ul>
	<a href='searchResults.html?searchTerm=chicken' >View all of our chicken dishes</a>
	<%@ include file="footer.jsp" %>
</body>
</html>