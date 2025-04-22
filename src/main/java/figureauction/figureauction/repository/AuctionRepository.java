package figureauction.figureauction.repository;

import figureauction.figureauction.domain.Auction;
import figureauction.figureauction.domain.Bid;
import figureauction.figureauction.domain.Item;
import figureauction.figureauction.domain.Notification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuctionRepository {
    List<Auction> findAll();
    int findItemIdByAuctionId(long auctionId);
    int findCurrentPrice(long auctionId);
    void saveAuction(Auction auction);
    void updateBid(Bid bid);
    void saveBid(Bid bid);
    void updatePrice(Auction auction);
    Auction findOne(Long auctionId);
    Bid findBid(Long auctionId);
    Bid findBidMaxPrice(Long auctionId);
    void reRegister(Long auctionId);
    void saveNotification(Notification notification);
    List<Notification> findUnreadByUserId(Long userId);
    List<Auction> findBySearchName(String searchName);
}
