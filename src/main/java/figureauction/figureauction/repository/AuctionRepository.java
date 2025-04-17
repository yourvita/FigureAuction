package figureauction.figureauction.repository;

import figureauction.figureauction.domain.Auction;
import figureauction.figureauction.domain.Bid;

import java.util.List;

public interface AuctionRepository {
    List<Auction> findAll();
    void saveAuction(Auction auction);
    void saveBid(Bid bid);
    void updatePrice(Auction auction);
    Auction findOne(Long auctionId);
    Bid findBid(Long auctionId);
    Bid findBidMaxPrice(Long auctionId);
}
