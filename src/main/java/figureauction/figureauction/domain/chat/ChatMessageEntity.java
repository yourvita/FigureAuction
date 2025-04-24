package figureauction.figureauction.domain.chat;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageEntity {
    private Long id;
    private String sender;
    private String recipient;
    private String message;
    private LocalDateTime timeStamp;
}
