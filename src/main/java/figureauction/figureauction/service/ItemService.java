package figureauction.figureauction.service;

import figureauction.figureauction.domain.Item;
import figureauction.figureauction.domain.ItemAuctionDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ItemService {
    Item saveItem(Item item);
    List<Item> findAll();
    Item findOne(long id);
    void update(long id, Item item);
    void deleteItem(long id);
    void bidUpdate(long id, int price);
    List<Item> findBySellerId(String sellerId);
    List<Item> findBySearchName(String searchName);
    Page<ItemAuctionDto> getPagedItemAuctions(int page, int size);
}
