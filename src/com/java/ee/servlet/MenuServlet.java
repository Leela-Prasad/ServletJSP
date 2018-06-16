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

public class MenuServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body><h1>XYZ Restaurant</h1><ul>");
		MenuDataService service = new MenuDataService();
		List<MenuItem> menuItems = service.getMenu();
		for(MenuItem menuItem : menuItems) {
			out.println("<li>" + menuItem + "</li>");
		}
		out.println("</ul></body></html>");
	}
	
}
