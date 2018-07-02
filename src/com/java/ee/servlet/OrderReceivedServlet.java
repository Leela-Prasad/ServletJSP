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

public class OrderReceivedServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MenuDataService menuService = new MenuDataService();
		List<MenuItem> menuItems = menuService.getMenu();
		
		double totalCost = 0;
		for(MenuItem menuItem : menuItems) {
			String quantity = request.getParameter("item_" + menuItem.getId());
			if(null!=quantity && !quantity.isEmpty()) {
				int q = Integer.parseInt(quantity);
				if(q>0) {
					totalCost += menuItem.getPrice()*q;
				}
			}
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body><p>");
		out.println("Total cost : " + totalCost);
		out.println("</p></body></html>");
		out.close();
	}
}
