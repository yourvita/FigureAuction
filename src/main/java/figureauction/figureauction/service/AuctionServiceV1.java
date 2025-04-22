package figureauction.figureauction.service;

import figureauction.figureauction.domain.Auction;
import figureauction.figureauction.domain.Bid;
import figureauction.figureauction.domain.Item;
import figureauction.figureauction.domain.Notification;
import figureauction.figureauction.repository.AuctionRepository;
import figureauction.figureauction.web.util.NotificationSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
@Slf4j
@RequiredArgsConstructor
public class AuctionServiceV1 implements AuctionService {
    private final AuctionRepository repository;
    private final ItemService itemService;
    private final MemberService memberService;
    private final NotificationSender sender;

    @Override
    public List<Auction> findAll() {
        return repository.findAll();
    }

    @Override
    public void saveAuction(Auction auction) {
        repository.saveAuction(auction);
    }

    @Override
    public int findCurrentPrice(long auctionId) {
        return repository.findCurrentPrice(auctionId);
    }

    @Override
    public void saveBid(Bid bid) {
        System.out.println("bid.getAuctionId() = " + bid.getAuctionId());
        Auction auction = repository.findOne((long) bid.getAuctionId());
        int itemId = repository.findItemIdByAuctionId(bid.getAuctionId());
        String itemName = itemService.findOne(itemId).getItemName();
        String message = "경매 :" + itemName + "에 상회입찰이 발생했습니다";
        Bid currentMaxBid = repository.findBidMaxPrice((long) bid.getAuctionId());
        log.warn(currentMaxBid.toString());
        log.warn("11111currentMaxBid.getBidderId():{}", currentMaxBid.getBidderId());
        log.warn("11111bid.getBidderId():{}", bid.getBidderId());
        if(currentMaxBid != null && currentMaxBid.getBidderId() != bid.getBidderId()) {
            log.warn("currentMaxBid.getBidderId():{}", currentMaxBid.getBidderId());
            log.warn("bid.getBidderId():{}", bid.getBidderId());
            Notification notification = new Notification();
            notification.setUserId(currentMaxBid.getBidderId());
            notification.setMessage(message);
            repository.saveNotification(notification);
            sender.sendBidOvertakenNotification(currentMaxBid.getBidderId(), message);
            log.info("message: {}",message);
        }

        repository.saveBid(bid);
        auction.setCurrentPrice(bid.getBidPrice());
        repository.updatePrice(auction);
    }

    @Override
    public void updatePrice(Auction auction) {
        repository.updatePrice(auction);
    }

    @Override
    public Auction findOne(Long auctionId) {
        return repository.findOne(auctionId);
    }

    @Override
    public Bid findBid(Long auctionId) {
        return repository.findBid(auctionId);
    }

    @Override
    public void regBid(long userId, long itemId, int bidUnit) {
        // itemId를 통해 클릭한 상품의 정보를 불러옴
        Item item = itemService.findOne(itemId);
        Auction auction = findOne(item.getItemId());

        int currentPrice = auction.getCurrentPrice() + bidUnit;
        // 입찰 정보 생성
        Bid bid = new Bid();
        bid.setAuctionId(auction.getAuctionId());
        bid.setBidderId(userId);
        bid.setBidPrice(currentPrice);

        // 가격 업데이트
        auction.setCurrentPrice(currentPrice);
        item.setPrice(currentPrice);

        // 입찰 정보 업데이트 및 저장
        itemService.bidUpdate(itemId, currentPrice);
        updatePrice(auction);
        if(repository.findBid((long) auction.getAuctionId()) == null) {
            repository.saveBid(bid);
        } else {
            Bid currentMaxBid = repository.findBidMaxPrice((long) bid.getAuctionId());
            if(currentMaxBid != null && currentMaxBid.getBidderId() != auction.getAuctionId()) {
                // 상회입찰이 발생해 그전 입찰자에게 알림
                String itemName = itemService.findOne(itemId).getItemName();
                String message = "경매 : " + itemName + "에 상회입찰이 발생했습니다";
                Notification notification = new Notification();
                notification.setUserId(currentMaxBid.getBidderId());
                notification.setMessage(message);
                repository.saveNotification(notification);
                sender.sendBidOvertakenNotification(currentMaxBid.getBidderId(), message);

                // 상회입찰한 금액을 bid에 업데이트
                repository.updateBid(bid);
            }
        }
    }

    @Override
    public void createItemAndAuction(Item item) {
        Item savedItem = itemService.findOne(item.getItemId());
        Auction auction = new Auction();
        auction.setItemId(savedItem.getItemId());
        auction.setStartPrice(savedItem.getPrice());
        auction.setCurrentPrice(savedItem.getPrice());
        auction.setStartTime(savedItem.getRegDate());
//        시간조정 endtime조정
        auction.setEndTime(savedItem.getRegDate().plusMinutes(3));
        saveAuction(auction);

    }

    @Override
    public Bid findBidMaxPrice(long auctionId) {
        return repository.findBidMaxPrice(auctionId);
    }

    @Override
    public String getAuctionBidderName(long auctionId) {
        if(findBidMaxPrice(auctionId)!=null) {
            Long bidderId = findBidMaxPrice(auctionId).getBidderId();
            return memberService.findById(bidderId).getUserName();
        }
        return null;
    }

    @Override
    public void reRegister(Long auctionId) {
        repository.reRegister(auctionId);
    }

    @Override
    public List<Notification> findUnreadByUserId(Long userId) {
        return repository.findUnreadByUserId(userId);
    }

    @Override
    public List<Auction> findBySearchName(String searchName) {
        return repository.findBySearchName(searchName);
    }
}
