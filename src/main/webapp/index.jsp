<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.java.ee.data.MenuDao" %>
<%@ page import="com.java.ee.data.MenuDaoFactory" %>
<%@ page import="com.java.ee.domain.MenuItem" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	MenuDao menuDao = MenuDaoFactory.getMenuDao();
	List<MenuItem> menuItems = menuDao.getFullMenu();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
</head>
<body>
	<%-- <jsp:include page="header.jsp" /> --%>
	<%@ include file="header.jsp" %>
	<ul>
	<%-- 	
		<% 
 			for (MenuItem menuItem : menuItems) {
 				out.println("<li>" + menuItem + "</li>");
 			}
 		%> 
 	--%>
 	
 	<%
 		for (MenuItem menuItem : menuItems) {
 	%>	
 		<li><%= menuItem %></li>
 	<% 
 		}
 	%>

	
	</ul>
	<%@ include file="footer.jsp" %>
	<%-- <jsp:include page="footer.jsp"></jsp:include> --%>
</body>
</html>