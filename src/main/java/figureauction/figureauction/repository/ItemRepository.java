package figureauction.figureauction.repository;

import figureauction.figureauction.domain.Item;
import figureauction.figureauction.domain.ItemAuctionDto;
import org.apache.ibatis.annotations.Param;

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
    List<ItemAuctionDto> findItemAuctionPage(int limit, int offset);
    int countItemAuctions();
    List<ItemAuctionDto> findByNameItemAuctionPage(String searchName, @Param("limit") int limit, @Param("offset") int offset);
    int countByNameItemAuctions(String searchName);
}
