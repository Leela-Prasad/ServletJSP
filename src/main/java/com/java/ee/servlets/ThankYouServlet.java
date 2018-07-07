package com.java.ee.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.ee.data.MenuDao;
import com.java.ee.data.MenuDaoFactory;
import com.java.ee.domain.Order;

@WebServlet("/thankYou.html")
@ServletSecurity(@HttpConstraint(rolesAllowed= {"user"}))
public class ThankYouServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		
		if (order == null) {
			response.sendRedirect("/order.html");
			return;
		}

		MenuDao menuDao = MenuDaoFactory.getMenuDao();
		
		request.setAttribute("total", menuDao.getOrderTotal(order.getId()));
		request.setAttribute("status", menuDao.getOrder(order.getId()).getStatus());
		request.setAttribute("id", order.getId());
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		String time = sdf.format(new Date());
		request.setAttribute("time", time);
		request.setAttribute("currencyCode", "USD");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("ThankYou.jsp");
		dispatcher.forward(request, response);
		
	}
}
