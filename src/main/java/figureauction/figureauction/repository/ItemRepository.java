package figureauction.figureauction.repository;

import figureauction.figureauction.domain.Item;

import java.util.List;

public interface ItemRepository {
    Item saveItem(Item item);
    List<Item> findAll();
    Item findOne(long id);
    void update(long id, Item item);
    void deleteItem(long id);
    void bidUpdate(long id, int price);
    List<Item> findBySellerId(String sellerId);
    List<Item> findBySearchName(String searchName);
}
