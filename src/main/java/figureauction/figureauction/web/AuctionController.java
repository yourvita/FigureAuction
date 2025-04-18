package figureauction.figureauction.web;

import figureauction.figureauction.domain.Auction;
import figureauction.figureauction.domain.Bid;
import figureauction.figureauction.domain.Item;
import figureauction.figureauction.domain.Notification;
import figureauction.figureauction.service.AuctionService;
import figureauction.figureauction.service.ItemService;
import figureauction.figureauction.web.util.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/auction")
@RequiredArgsConstructor
public class AuctionController {
    private final AuctionService service;

    @PostMapping("/{itemId}/bid")
    public String bid(@PathVariable long itemId,
                      @RequestParam int bidUnit,
                      HttpSession session) {
        // 로그인한 사용자의 ID를 불러옴
        Long userId = (Long) session.getAttribute("userId");
        service.regBid(userId, itemId, bidUnit);

        return "redirect:/item/" + itemId;
    }

    @GetMapping("/notifications")
    public String userNotifications(HttpSession session, Model model) {
        Long userId = SessionUtil.getLoginUserId(session);
        List<Notification> notifications= service.findUnreadByUserId(userId);
        model.addAttribute("notifications", notifications);
        return "user/notifications";
    }
}
