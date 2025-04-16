package figureauction.figureauction.service;

import figureauction.figureauction.domain.Auction;
import figureauction.figureauction.repository.ItemRepository;
import figureauction.figureauction.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceV1 implements ItemService {

    private final ItemRepository itemRepository;
    private final AuctionService auctionService;

    @Override
    public Item saveItem(Item item) {
        return itemRepository.saveItem(item);
    }

    public Item createItemWithAuction(Item item) {
        itemRepository.saveItem(item);
        Item savedItem = findOne(item.getItemId());
        Auction auction = new Auction();
        auction.setItemId(savedItem.getItemId());
        auction.setStartPrice(savedItem.getPrice());
        auction.setCurrentPrice(savedItem.getPrice());
        auction.setStartTime(savedItem.getRegDate());
        auction.setEndTime(savedItem.getRegDate().plusDays(1));
        auctionService.saveAuction(auction);

        return savedItem;
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item findOne(long id) {
        return itemRepository.findOne(id);
    }

    @Override
    public void update(long id, Item item) {
        itemRepository.update(id, item);
    }

    @Override
    public void deleteItem(long id) {
        itemRepository.deleteItem(id);
    }

    @Override
    public void bidUpdate(long id, int price) {
        itemRepository.bidUpdate(id, price);
    }

    @Override
    public List<Item> findBySellerId(String sellerId) {
        return itemRepository.findBySellerId(sellerId);
    }

}
