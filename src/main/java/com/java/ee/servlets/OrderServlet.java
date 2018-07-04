package com.java.ee.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.ee.data.MenuDao;
import com.java.ee.data.MenuDaoFactory;
import com.java.ee.domain.MenuItem;

@WebServlet("/order.html")
@ServletSecurity(@HttpConstraint(rolesAllowed= {"user"}))
public class OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body><h1>Ricky's Restaurant</h1>");
		out.println("<h2>Order your food</h2>");
		
		out.println("<form action='orderReceived.html' method='POST' >");
		out.println("<ul>");
		
		MenuDao menuDao = MenuDaoFactory.getMenuDao();
		List<MenuItem> menuItems = menuDao.getFullMenu();
		
		for (MenuItem menuItem : menuItems) {
			out.println("<li>" + menuItem + "<input type='text' name='item_" +menuItem.getId() +"' /> </li>");
		}
		
		out.println("</ul>");
		out.println("<input type='submit' />");
		out.println("</form></body></html>");
		out.close();
	}
}
