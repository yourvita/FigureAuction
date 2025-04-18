package figureauction.figureauction.mapper;

import figureauction.figureauction.domain.Auction;
import figureauction.figureauction.domain.Bid;
import figureauction.figureauction.domain.Notification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuctionMapper {
    List<Auction> findAll();
    int findItemIdByAuctionId(long auctionId);
    int findCurrentPrice(long auctionId);
    void updateBid(Bid bid);
    void saveAuction(Auction auction);
    void saveBid(Bid bid);
    void updatePrice(Auction auction);
    Auction findOne(Long auctionId);
    Bid findBid(Long auctionId);
    Bid findBidMaxPrice(Long auctionId);
    void reRegister(Long auctionId);
    void saveNotification(Notification notification);
    List<Notification> findUnreadByUserId(Long userId);
}
