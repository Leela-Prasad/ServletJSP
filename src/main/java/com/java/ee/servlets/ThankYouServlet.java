package com.java.ee.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/thankYou.html")
@ServletSecurity(@HttpConstraint(rolesAllowed= {"user"}))
public class ThankYouServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		HttpSession session = request.getSession();
		Double total = (Double) session.getAttribute("total");
		
		if (total == null) {
			response.sendRedirect("/order.html");
			return;
		}
		
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		out.println("<html><body><h1>Ricky's Restaurant</h1>");
		out.println("<h2>Order your food</h2>");
		
		out.println("Thank you - your order has been received. You need to pay $" + total);
				
		out.println("</body></html>");
		out.close();
		
	}
}
