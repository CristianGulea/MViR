package ro.ubb.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class SocketTextHandler extends TextWebSocketHandler {

    private static Map<String, WebSocketSession> sessionsDetails = new HashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        sessionsDetails.put(payload, session);
    }


    public void notifyClient(String userId) throws IOException {
        if (sessionsDetails.containsKey(userId)){
            System.out.println("Refresh to " + userId);
            sessionsDetails.get(userId).sendMessage(new TextMessage("Refresh"));
        }
    }
}
