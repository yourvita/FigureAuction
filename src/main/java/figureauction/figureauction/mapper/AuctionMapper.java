package figureauction.figureauction.mapper;

import figureauction.figureauction.domain.Auction;
import figureauction.figureauction.domain.Bid;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuctionMapper {
    void saveAuction(Auction auction);
    void saveBid(Bid bid);
    void updatePrice(Auction auction);
    Auction findOne(Long auctionId);
    Bid findBid(Long auctionId);
}
