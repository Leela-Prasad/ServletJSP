package com.java.ee.websockets;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import com.java.ee.data.MenuDao;
import com.java.ee.data.MenuDaoFactory;

@ServerEndpoint("/kitchenManagement")
public class KitchenDisplayWebSocket {

	private KitchenDisplaySessionHandler handler = KitchenDisplaySessionHandler.getHandler();
	
	@OnOpen
	public void open(Session session) {
		handler.addSession(session);
		KitchenDisplaySessionHandler handler = KitchenDisplaySessionHandler.getHandler();
		handler.sendAllOrders(session);
	}
	
	@OnClose
	public void close(Session session) {
		handler.removeSession(session);
	}
	
	@OnError
	public void onError(Throwable error) {
		throw new RuntimeException(error);
	}
	
	@OnMessage
	public void handleMessage(String message,Session session) {
		JSONObject json = new JSONObject(message);
		Long orderId = json.getLong("id");
		String status = json.getString("status");
		
		MenuDao menuDao = MenuDaoFactory.getMenuDao();
		menuDao.updateOrderStatus(orderId,status);
		KitchenDisplaySessionHandler handler = KitchenDisplaySessionHandler.getHandler();
		handler.ammendOrder(menuDao.getOrder(orderId));
	}
	
}
