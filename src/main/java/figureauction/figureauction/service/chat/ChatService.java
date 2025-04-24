package figureauction.figureauction.service.chat;

import figureauction.figureauction.domain.chat.ChatMessage;
import figureauction.figureauction.domain.chat.ChatMessageEntity;
import figureauction.figureauction.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public void saveMessage(ChatMessage message) {
        ChatMessageEntity entity = new ChatMessageEntity();
        entity.setSender(message.getSender());
        entity.setRecipient(message.getRecipient());
        entity.setMessage(message.getMessage());
        entity.setTimeStamp(message.getTimeStamp());
        chatRepository.saveMessage(entity);
    }

    public List<ChatMessageEntity> getMessages(String sender, String recipient) {
        return chatRepository.findBySenderReceiver(sender, recipient);
    }
}
