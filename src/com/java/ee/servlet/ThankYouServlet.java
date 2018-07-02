package com.java.ee.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ThankYouServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		
		String totalCost = request.getParameter("total");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body><p>");
		out.println("Total cost : " + totalCost);
		out.println("</p></body></html>");
		out.close();
	} 
}
