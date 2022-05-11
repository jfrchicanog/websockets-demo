package es.uma.informatica.sii.websockets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/prueba")
public class WebsocketEndpoint{
	
	private static Logger LOGGER = Logger.getLogger(WebsocketEndpoint.class.getCanonicalName());
	
	@OnMessage
	public void onMessage (Session session, String message) {
		LOGGER.info("Recibido el mensaje: "+message);
		for (Session s: session.getOpenSessions()) {
			if (s.isOpen()) {
				try {
					s.getBasicRemote().sendText(message);
					LOGGER.info("Enviado el mensaje: "+message);
				} catch (IOException e) {
					LOGGER.warning("Error enviando mensaje a websocket "+s.getId());
				}
			}
		}
	}
	
	@OnOpen
	public void onOpen (Session session, EndpointConfig config) {
		LOGGER.info("Se abre un nuevo websocket: "+session.getId());
	}
	
	@OnClose
	public void onClose (Session session, CloseReason reason) {
		LOGGER.info("Se cierra un nuevo websocket ("+session.getId()+") porque "
				+reason.getReasonPhrase());
	}
	
	@OnError
	public void onError (Session session, Throwable problem) {
		LOGGER.info("Error en websocket: "+session.getId()+" por "+problem.getMessage());
	}

}
