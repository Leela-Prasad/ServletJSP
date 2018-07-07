<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<h1>Ricky's Restaurant</h1>
	<h2>Menu</h2>
	<ul>
	 	<c:forEach items="${menuItems}" var="menuItem">
	 		<%-- <li><c:out value="${menuItem} - ${menuItem.description}"></c:out></li> --%>
	 		<li>${menuItem} - ${menuItem.description}</li>
	 	</c:forEach>
	</ul>
	
	<jsp:include page="footer.jsp" />
</body>
</html>