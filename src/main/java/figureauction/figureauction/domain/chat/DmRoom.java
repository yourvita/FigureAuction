package figureauction.figureauction.domain.chat;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DmRoom {
    private Long roomId;
    private String senderEmail;
    private String recipientEmail;
    private LocalDateTime createdAt;
}
