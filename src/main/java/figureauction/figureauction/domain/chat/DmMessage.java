package figureauction.figureauction.domain.chat;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DmMessage {
    private Long roomId;
    private String senderEmail;
    private String recipientEmail;
    private String content;
    private LocalDateTime createdAt;
}
