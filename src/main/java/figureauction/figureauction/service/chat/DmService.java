package figureauction.figureauction.service.chat;

import figureauction.figureauction.domain.chat.DmMessage;
import figureauction.figureauction.domain.chat.DmRoom;
import figureauction.figureauction.mapper.DmRoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DmService {
    private final DmRoomMapper roomMapper;

    public Long findOrCreateRoom(String memberEmail, String targetEmail) {
        DmRoom room = roomMapper.findRoom(memberEmail, targetEmail);
        if(room != null) {
            return room.getRoomId();
        }
        DmRoom newRoom = new DmRoom();
        newRoom.setSenderEmail(memberEmail);
        newRoom.setRecipientEmail(targetEmail);
        roomMapper.createRoom(newRoom);
        return newRoom.getRoomId();
    }

    public List<DmRoom> dmList(String recipientEmail) {
        return roomMapper.dmList(recipientEmail);
    }

    public List<DmMessage> messageList(String senderEmail, String recipientEmail) {
        return roomMapper.messageList(senderEmail, recipientEmail);
    }

    public void saveMessage(DmMessage message) {
        roomMapper.saveMessage(message);
    }
}
