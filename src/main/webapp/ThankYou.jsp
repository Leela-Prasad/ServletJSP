<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thank You</title>
<script>
	function updateStatus() {
		var request = new XMLHttpRequest();
		request.onreadystatechange = function() {
			if(this.readyState == 4) {
				var jsonResponse = JSON.parse(this.responseText);
				document.getElementById("status").innerHTML = jsonResponse.status;
				document.getElementById("time").innerHTML = jsonResponse.time;
			}
		}
		request.open("GET","updateStatus?id=${id}",true);
		request.send();
	}
</script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<h1>Ricky's Restaurant</h1>
	<h2>Order your food</h2>
	<%-- <c:out value="Thank you - your order has been received. You need to pay $ ${total}" /> --%>
	<p>Thank you - your order has been received. You need to pay 
	<fmt:formatNumber value="${total}" type="currency" currencyCode="${currencyCode}" /></p>
	
	<p>Your Order Status : <span id="status">${status}</span></p>
	<p>Last Updated : <span id="time">${time}</span></p>
	<input type="button" value="refresh status" onclick="updateStatus()"/>
	
	<jsp:include page="footer.jsp" />
</body>
</html>