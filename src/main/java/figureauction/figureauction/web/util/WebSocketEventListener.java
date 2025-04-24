package figureauction.figureauction.web.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final SimpMessageSendingOperations operations;

/*    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("Connected to websocket");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String userName = (String) headerAccessor.getSessionAttributes().get("userName");
        if (userName != null) {
            log.info("Disconnected from websocket : {}", userName);

            ChatMessage message = new ChatMessage();
            message.setType("LEAVE");
            message.setSender(userName);

            operations.convertAndSend("/topic/public", message);
        }
    }*/
}
