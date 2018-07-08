package com.java.ee.websockets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.websocket.Session;

import org.json.JSONObject;

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
	
	public void newOrder(Order order) {
		JSONObject json = new JSONObject();
		json.put("id", order.getId());
		json.put("status",order.getStatus());
		json.put("content", order.toString());
		json.put("action", "add");
		json.put("update", new Date());
		sendMessage(json);
	}

	public void ammendOrder(Order order) {
		JSONObject json = new JSONObject();
		json.put("id", order.getId());
		json.put("action", "remove");
		sendMessage(json);
		
		newOrder(order);
	}
}
