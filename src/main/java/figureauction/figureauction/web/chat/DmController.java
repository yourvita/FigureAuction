package figureauction.figureauction.web.chat;

import figureauction.figureauction.domain.chat.DmMessage;
import figureauction.figureauction.domain.chat.DmRoom;
import figureauction.figureauction.service.chat.DmService;
import figureauction.figureauction.web.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class DmController {

    private final DmService dmService;
    private final SimpMessagingTemplate messagingTemplate;


    @GetMapping("/dmList/{userEmail}")
    public String dmList(@PathVariable String userEmail,
                         HttpSession session,
                         Model model) {
        SessionUtil.setLoginAttributes(model, session);

        List<DmRoom> dmRooms = dmService.dmList(userEmail);
        model.addAttribute("dmRooms", dmRooms);
        return "user/dmList";
    }

//    발신자가 DM 대화방 만들기
    @GetMapping("/dm/{targetEmail}")
    public String enterDmRoom(@PathVariable String targetEmail, HttpSession session, Model model) {
        SessionUtil.setLoginAttributes(model, session);

        String myId = (String) session.getAttribute("userEmail");
        Long roomId = dmService.findOrCreateRoom(myId, targetEmail);
        List<DmMessage> dmMessages = dmService.messageList(myId, targetEmail);

        model.addAttribute("dmMessages", dmMessages);
        model.addAttribute("roomId", roomId);
        model.addAttribute("senderId", myId);
        model.addAttribute("targetId", targetEmail);

        return "user/dmroom";
    }

    @GetMapping("/deleteDm/{targetEmail}")
    public String deleteDm(@PathVariable String targetEmail, HttpSession session, Model model) {
        SessionUtil.setLoginAttributes(model, session);
        String myId = (String) session.getAttribute("userEmail");
        dmService.deleteDmRoom(myId, targetEmail);
        return "redirect:/user/dmList/" + myId;
    }

    @MessageMapping("/chat.send")
    public void sendDm(DmMessage message) {
        message.setCreatedAt(LocalDateTime.now());
        dmService.saveMessage(message); // DB저장

        // 메시지 보내기
        messagingTemplate.convertAndSend("/queue/dm/" + message.getRecipientEmail(), message);
        messagingTemplate.convertAndSend("/queue/dm/" + message.getSenderEmail(), message);

    }
}
