package com.java.ee.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.java.ee.data.MenuDao;
import com.java.ee.data.MenuDaoFactory;

@WebServlet("/updateStatus")
public class OrderStatusAJAXServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long orderId = Long.valueOf(request.getParameter("id"));
		
		MenuDao menuDao = MenuDaoFactory.getMenuDao();
		String status = menuDao.getOrder(orderId).getStatus();
		
		PrintWriter out = response.getWriter();
		
		JSONObject json = new JSONObject();
		json.put("status", status);
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		String time = sdf.format(new Date());
		json.put("time", time);
		
		out.println(json.toString());
		out.close();
	}
}
