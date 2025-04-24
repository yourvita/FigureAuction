package figureauction.figureauction.repository;

import figureauction.figureauction.domain.chat.ChatMessageEntity;
import figureauction.figureauction.mapper.ChatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatRepository {
    private final ChatMapper chatMapper;

    public void saveMessage(ChatMessageEntity message) {
        chatMapper.saveMessage(message);
    }

    public List<ChatMessageEntity> findBySenderReceiver(String sender, String recipient) {
        return chatMapper.findBySenderReceiver(sender, recipient);
    }
}
