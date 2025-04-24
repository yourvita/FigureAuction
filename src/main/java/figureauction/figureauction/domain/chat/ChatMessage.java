package figureauction.figureauction.domain.chat;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@Setter
public class ChatMessage {
    private String sender;
    private String recipient;
    private String message;
    private String type;
    private LocalDateTime timeStamp;
}
