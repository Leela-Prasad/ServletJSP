package com.java.ee.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.ee.data.MenuDao;
import com.java.ee.data.MenuDaoFactory;
import com.java.ee.domain.Order;
import com.java.ee.websockets.KitchenDisplaySessionHandler;

@WebServlet("/processorder.html")
public class ProcessOrderServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { 
	MenuDao menuDao = MenuDaoFactory.getMenuDao();
	List<Order> orders;
	orders = menuDao.getAllOrders();
	request.setAttribute("orders", orders);
	
	List<String> statuses = new ArrayList<String>();
	statuses.add("order accepted");
	statuses.add("payment received");
	statuses.add("being prepared");
	statuses.add("ready for collection");
	
	request.setAttribute("statuses", statuses);

	ServletContext context = getServletContext();
	RequestDispatcher dispatch = context.getRequestDispatcher("/processorder.jsp");
	dispatch.forward(request,response);
	}
	
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { 
		MenuDao menuDao = MenuDaoFactory.getMenuDao();
		Long id = Long.valueOf(request.getParameter("id"));
		String status =  request.getParameter("status");
		System.out.println(id + " : " + status);
		menuDao.updateOrderStatus(id,status);
		KitchenDisplaySessionHandler handler = KitchenDisplaySessionHandler.getHandler();
		handler.ammendOrder(menuDao.getOrder(id));
		doGet(request,response);
		}
}
