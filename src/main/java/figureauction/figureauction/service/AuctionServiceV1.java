package figureauction.figureauction.service;

import figureauction.figureauction.domain.Auction;
import figureauction.figureauction.domain.Bid;
import figureauction.figureauction.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuctionServiceV1 implements AuctionService {
    private final AuctionRepository repository;

    @Override
    public void saveAuction(Auction auction) {
        repository.saveAuction(auction);
    }

    @Override
    public void saveBid(Bid bid) {
        repository.saveBid(bid);
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
}
