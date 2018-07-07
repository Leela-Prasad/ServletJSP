<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Squares</title>
</head>
<body>
	<h1>First 10 Squares</h1>
	<c:forEach begin="0" end="10" var="i">
		<c:out value ="${i * i}" />
	</c:forEach>
</body>
</html>