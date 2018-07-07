package com.java.ee.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
	
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		HttpSession session = request.getSession();
		Double total = (Double) session.getAttribute("total");
		
		if (total == null) {
			response.sendRedirect("/order.html");
			return;
		}

		request.setAttribute("total", total);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ThankYou.jsp");
		dispatcher.forward(request, response);
		
	}
}
