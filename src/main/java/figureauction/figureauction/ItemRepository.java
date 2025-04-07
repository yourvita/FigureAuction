package figureauction.figureauction;

import figureauction.figureauction.domain.Item;

import java.util.List;

public interface ItemRepository {
    Item saveItem(Item item);
    List<Item> findAll();
}
