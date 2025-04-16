package figureauction.figureauction.service;

import figureauction.figureauction.domain.Auction;
import figureauction.figureauction.domain.Bid;
import figureauction.figureauction.domain.Item;
import figureauction.figureauction.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuctionServiceV1 implements AuctionService {
    private final AuctionRepository repository;
    private final ItemService itemService;

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
        saveBid(bid);
    }

    @Override
    public Item createItemAndAuction(Item item) {
        Item savedItem = itemService.findOne(item.getItemId());
        Auction auction = new Auction();
        auction.setItemId(savedItem.getItemId());
        auction.setStartPrice(savedItem.getPrice());
        auction.setCurrentPrice(savedItem.getPrice());
        auction.setStartTime(savedItem.getRegDate());
        auction.setEndTime(savedItem.getRegDate().plusDays(1));
        saveAuction(auction);

        return savedItem;
    }
}
