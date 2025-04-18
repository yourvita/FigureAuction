package figureauction.figureauction.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter@Setter
public class Notification {
    private long userId;
    private String message;
    private boolean isRead;
    private LocalDateTime createdAt;
}
