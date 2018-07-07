<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.java.ee.domain.MenuItem" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Results</title>
</head>
<body>
	<h2>Your Search Results : </h2>
	<jsp:include page="header.jsp"></jsp:include>
	
	<%
		List<MenuItem> menuItems = (List<MenuItem>)request.getAttribute("menuItems");
		String searchTerm = (String)request.getAttribute("searchTerm");
	
		if (menuItems.size() > 0) {
			for(MenuItem menuItem : menuItems) {
				out.println("<li>" + menuItem + " " + menuItem.getDescription() + "</li>");
			}
		}else {
			out.println("<p>I'm sorry, there are no dishes containing " + searchTerm);
		}
	%>
	
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>