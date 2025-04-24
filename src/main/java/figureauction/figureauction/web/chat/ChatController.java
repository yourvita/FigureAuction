package figureauction.figureauction.web.chat;

import figureauction.figureauction.domain.chat.ChatMessage;
import figureauction.figureauction.domain.chat.ChatMessageEntity;
import figureauction.figureauction.domain.Member;
import figureauction.figureauction.service.chat.ChatService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate template;

    @GetMapping("/chat/{recipient}")
    public String chatForm(@PathVariable String recipient,Model model, HttpSession session, Member member) {
        String userEmail = (String) session.getAttribute("userEmail");
        List<ChatMessageEntity> messages = chatService.getMessages(userEmail, recipient);

        model.addAttribute("messages", messages);
        model.addAttribute("recipient", recipient);
        model.addAttribute("userEmail", userEmail);

        return "/user/chat";
    }
    @MessageMapping("/chat.send")
    public void sendMessage(ChatMessage message) {
        message.setMessage(message.getMessage());
        message.setTimeStamp(LocalDateTime.now());

        chatService.saveMessage(message);

        if (message.getRecipient() != null) {
            template.convertAndSend("/queue/" + message.getRecipient(), message);
        } else {
            template.convertAndSend("/topic/message", message);
        }
    }


}
