package com.java.ee.websockets;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/kitchenManagement")
public class KitchenDisplayWebSocket {

	private KitchenDisplaySessionHandler handler = KitchenDisplaySessionHandler.getHandler();
	
	@OnOpen
	public void open(Session session) {
		handler.addSession(session);
	}
	
	@OnClose
	public void close(Session session) {
		handler.removeSession(session);
	}
	
	@OnError
	public void onError(Throwable error) {
		throw new RuntimeException(error);
	}
	
}
