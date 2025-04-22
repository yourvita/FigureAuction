package figureauction.figureauction.mapper;

import figureauction.figureauction.domain.Item;
import figureauction.figureauction.domain.ItemAuctionDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {
    void saveItem(Item item);
    List<Item> findAll();
    Item findOne(long id);
    void update(@Param("id") long id, @Param("item") Item item);
    void deleteItem(long id);
    void bidUpdate(long id, int price);
    List<Item> findBySellerId(String sellerId);
    List<Item> findBySearchName(String searchName);
    List<ItemAuctionDto> findItemAuctionPage(@Param("limit") int limit, @Param("offset") int offset);
    int countItemAuctions();
    List<ItemAuctionDto> findByNameItemAuctionPage(String searchName, @Param("limit") int limit, @Param("offset") int offset);
    int countByNameItemAuctions(String searchName);
}
