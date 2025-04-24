package figureauction.figureauction.mapper;

import figureauction.figureauction.domain.chat.ChatMessageEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {
    void saveMessage(ChatMessageEntity message);
    List<ChatMessageEntity> findBySenderReceiver(String sender, String recipient);

}
