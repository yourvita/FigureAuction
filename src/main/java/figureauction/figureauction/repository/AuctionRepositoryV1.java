package figureauction.figureauction.repository;

import figureauction.figureauction.domain.Auction;
import figureauction.figureauction.domain.Bid;
import figureauction.figureauction.mapper.AuctionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
public class AuctionRepositoryV1 implements AuctionRepository {

    private final AuctionMapper mapper;

    @Override
    public List<Auction> findAll() {
        return mapper.findAll();
    }

    @Override
    public void saveAuction(Auction auction) {
        mapper.saveAuction(auction);
    }

    @Override
    public void saveBid(Bid bid) {
        mapper.saveBid(bid);
    }

    @Override
    public void updatePrice(Auction auction) {
        mapper.updatePrice(auction);
    }

    @Override
    public Auction findOne(Long auctionId) {
        return mapper.findOne(auctionId);
    }

    @Override
    public Bid findBid(Long auctionId) {
        return mapper.findBid(auctionId);
    }

    @Override
    public Bid findBidMaxPrice(Long auctionId) {
        return mapper.findBidMaxPrice(auctionId);
    }

    @Override
    public void reRegister(Long auctionId) {
        mapper.reRegister(auctionId);
    }
}
