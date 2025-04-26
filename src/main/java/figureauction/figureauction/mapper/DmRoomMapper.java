package figureauction.figureauction.mapper;

import figureauction.figureauction.domain.chat.DmMessage;
import figureauction.figureauction.domain.chat.DmRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DmRoomMapper {
    DmRoom findRoom(String senderEmail, String recipientEmail);
    List<DmRoom> dmList(String recipientEmail);
    List<DmMessage> messageList(String senderEmail, String recipientEmail);
    void createRoom(DmRoom room);
    void saveMessage(DmMessage message);

}
