<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thank You</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<h1>Ricky's Restaurant</h1>
	<h2>Order your food</h2>
	<p>Thank you - your order has been received. You need to pay $<%=request.getAttribute("total") %></p>
	<%@ include file="footer.jsp" %>
</body>
</html>