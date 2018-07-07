package com.java.ee.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.ee.data.MenuDao;
import com.java.ee.data.MenuDaoFactory;
import com.java.ee.domain.MenuItem;

@WebServlet("/searchResults.html")
public class MenuSearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String searchTerm = request.getParameter("searchTerm");
		
		MenuDao menuDao = MenuDaoFactory.getMenuDao();
		List<MenuItem> menuItems = menuDao.find(searchTerm);
		
		request.setAttribute("menuItems", menuItems);
		request.setAttribute("searchTerm", searchTerm);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("SearchResults.jsp");
		dispatcher.forward(request, response);
		
	}
}
