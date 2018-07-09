package com.java.ee.websockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.websocket.Session;

import org.json.JSONObject;

import com.java.ee.data.MenuDao;
import com.java.ee.data.MenuDaoFactory;
import com.java.ee.domain.Order;

public class KitchenDisplaySessionHandler {

	private List<Session> sessions = new ArrayList<>();
	private static KitchenDisplaySessionHandler handler;
	
	private KitchenDisplaySessionHandler() {
		
	}
	
	public static KitchenDisplaySessionHandler getHandler() {
		if(null == handler) {
			synchronized (KitchenDisplaySessionHandler.class) {
				if(null == handler) {
					handler = new KitchenDisplaySessionHandler();
				}
			}
		}
		return handler;
	}
	
	public void addSession(Session session) {
		sessions.add(session);
	}
	
	public void removeSession(Session session) {
		sessions.remove(session);
	}
	
	private void sendMessage(JSONObject json) {
		for(Session session : sessions) {
			try {
				session.getBasicRemote().sendText(json.toString());
			} catch (IOException e) {
				removeSession(session);
			}
		}
	}
	
	private void sendMessage(JSONObject json,Session session) {
		try {
			session.getBasicRemote().sendText(json.toString());
		} catch (IOException e) {
			removeSession(session);
		}
	}
	
	private JSONObject generateJSONForObject(Order order) {
		JSONObject json = new JSONObject();
		json.put("id", order.getId());
		json.put("status",order.getStatus());
		json.put("content", order.toString());
		json.put("action", "add");
		json.put("update", new Date());
		return json;
	}
	
	public void newOrder(Order order) {
		if(!order.getStatus().equals("ready for collection"))
			sendMessage(generateJSONForObject(order));
	}

	public void ammendOrder(Order order) {
		JSONObject json = new JSONObject();
		json.put("id", order.getId());
		json.put("action", "remove");
		sendMessage(json);
		
		newOrder(order);
	}
	
	public void sendAllOrders(Session session) {
		MenuDao menuDao = MenuDaoFactory.getMenuDao();
		List<Order> orders = menuDao.getAllOrders();
		for(Order order : orders) {
			if(!order.getStatus().equals("ready for collection"))
				sendMessage(generateJSONForObject(order),session);
		}
	}
}
