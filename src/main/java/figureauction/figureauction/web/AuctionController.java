package figureauction.figureauction.web;

import figureauction.figureauction.domain.Auction;
import figureauction.figureauction.domain.Bid;
import figureauction.figureauction.domain.Item;
import figureauction.figureauction.service.AuctionService;
import figureauction.figureauction.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/auction")
@RequiredArgsConstructor
public class AuctionController {
    private final AuctionService auctionService;
    private final ItemService itemService;

    @PostMapping("/{itemId}/bid")
    public String bid(@PathVariable long itemId, @RequestParam int bidUnit, Model model, HttpServletRequest request) {
        // 로그인한 사용자의 ID를 불러옴
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

        // itemId를 통해 클릭한 상품의 정보를 불러옴
        Item item = itemService.findOne(itemId);
        Auction auction = auctionService.findOne(itemId);
        // TODO 왜 auction이 null이지?
        // bid 테이블에 입찰자와 입찰정보를 저장
        Bid bid = new Bid();
        bid.setAuctionId(auction.getAuctionId());
        bid.setBidderId(userId);
        int currentPrice = auction.getCurrentPrice() + bidUnit;
        bid.setBidPrice(currentPrice);

        log.info("auctionCurrentPrice {}", auction.getCurrentPrice());
        log.info("bidUnit{}", bidUnit);

        auction.setCurrentPrice(currentPrice);
        item.setPrice(currentPrice);

        log.info("afterAuctionCurrentPrice {}", auction.getCurrentPrice());
        log.info("call auctionService from AuctionController");

        itemService.bidUpdate(itemId, currentPrice);
        auctionService.updatePrice(auction);
        auctionService.saveBid(bid);

        return "redirect:/item/" + itemId;
    }
}
