package figureauction.figureauction.service;


import figureauction.figureauction.domain.Auction;
import figureauction.figureauction.domain.Bid;

public interface AuctionService {
    void saveAuction(Auction auction);
    void saveBid(Bid bid);
    void updatePrice(Auction auction);
    Auction findOne(Long auctionId);
    Bid findBid(Long auctionId);
    void regBid(long itemId, long userId, int bidUnit);
}
