package figureauction.figureauction.mapper;

import figureauction.figureauction.domain.Auction;
import figureauction.figureauction.domain.Bid;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuctionMapper {
    List<Auction> findAll();
    void saveAuction(Auction auction);
    void saveBid(Bid bid);
    void updatePrice(Auction auction);
    Auction findOne(Long auctionId);
    Bid findBid(Long auctionId);
    Bid findBidMaxPrice(Long auctionId);
}
