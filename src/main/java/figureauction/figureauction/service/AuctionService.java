package figureauction.figureauction.service;


import figureauction.figureauction.domain.Auction;
import figureauction.figureauction.domain.Bid;
import figureauction.figureauction.domain.Item;

import java.util.List;

public interface AuctionService {
    List<Auction> findAll();
    void saveAuction(Auction auction);
    void saveBid(Bid bid);
    void updatePrice(Auction auction);
    Auction findOne(Long auctionId);
    Bid findBid(Long auctionId);
    void regBid(long itemId, long userId, int bidUnit);
    void createItemAndAuction(Item item);
    Bid findBidMaxPrice(long auctionId);
    String getAuctionBidderName(long auctionId);
    void reRegister(Long auctionId);
}
