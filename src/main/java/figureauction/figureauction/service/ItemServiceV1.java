package figureauction.figureauction.service;

import figureauction.figureauction.domain.Auction;
import figureauction.figureauction.domain.ItemAuctionDto;
import figureauction.figureauction.repository.ItemRepository;
import figureauction.figureauction.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ItemServiceV1 implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item saveItem(Item item) {
        return itemRepository.saveItem(item);
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

    @Override
    public List<Item> findBySearchName(String searchName) {
        return itemRepository.findBySearchName(searchName);
    }

}
