package figureauction.figureauction.web.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationSender {
    private final SimpMessagingTemplate messagingTemplate;

    /* 상회입찰 발생 시 알림 */
    public void sendBidOvertakenNotification(Long userId, String message) {
        messagingTemplate.convertAndSend("/topic/notify/" + userId, message);
    }
}
