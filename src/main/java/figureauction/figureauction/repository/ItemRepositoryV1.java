package figureauction.figureauction.repository;

import figureauction.figureauction.domain.Item;
import figureauction.figureauction.domain.ItemAuctionDto;
import figureauction.figureauction.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
public class ItemRepositoryV1 implements ItemRepository {

    private final ItemMapper itemMapper;


    @Override
    public Item saveItem(Item item) {
        itemMapper.saveItem(item);
        return item;
    }

    @Override
    public List<Item> findAll() {
        return itemMapper.findAll();
    }

    @Override
    public Item findOne(long id) {
        return itemMapper.findOne(id);
    }

    @Override
    public void update(long id, Item item) {
        itemMapper.update(id, item);
    }

    @Override
    public void deleteItem(long id) {
        itemMapper.deleteItem(id);
    }

    @Override
    public void bidUpdate(long id, int price) {
        itemMapper.bidUpdate(id, price);
    }

    @Override
    public List<Item> findBySellerId(String sellerId) {
        return itemMapper.findBySellerId(sellerId);
    }

    @Override
    public List<Item> findBySearchName(String searchName) {
        return itemMapper.findBySearchName(searchName);
    }

    @Override
    public List<ItemAuctionDto> findItemAuctionPage(int limit, int offset) {
        return itemMapper.findItemAuctionPage(limit, offset);
    }

    @Override
    public int countItemAuctions() {
        return itemMapper.countItemAuctions();
    }

    @Override
    public List<ItemAuctionDto> findByNameItemAuctionPage(String searchName, int limit, int offset) {
        return itemMapper.findByNameItemAuctionPage(searchName, limit, offset);
    }

    @Override
    public int countByNameItemAuctions(String searchName) {
        return itemMapper.countByNameItemAuctions(searchName);
    }


}
