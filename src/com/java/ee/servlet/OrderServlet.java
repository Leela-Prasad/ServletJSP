package com.java.ee.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.ee.domain.MenuItem;
import com.java.ee.service.MenuDataService;

public class OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body><h1>Order Your Food!</h1>");
		out.println("<form action='orderReceived.html' method='POST'> ");
		MenuDataService menuService = new MenuDataService();
		List<MenuItem> items = menuService.getMenu();
		for(MenuItem item : items) {
			out.println("<li>"  + item.getName() + "<input type='text' name='item_"+ item.getId() + "' id='item_"+ item.getId() + "' /></li>");
		}
		out.println("<input type='submit' />");
		out.println("</form></body></html>");
	}
}
