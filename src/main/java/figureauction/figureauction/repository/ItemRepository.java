package figureauction.figureauction.repository;

import figureauction.figureauction.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> items = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setItemId(++sequence);
        items.put(item.getItemId(), item);

        return item;
    }

    public List<Item> findAll() {
        return new ArrayList<>(items.values());
    }


    public List<Item> findByItemName(String itemName) {
        List<Item> findItem = new ArrayList<>();
        for(Item i : items.values()) {
            if(i.getItemName().equals(itemName)) {
                findItem.add(i);
            }
        }
        return findItem;
    }

    public List<Item> findBySellerId(String sellerId) {
        List<Item> findItem = new ArrayList<>();
        for(Item i : items.values()) {
            if(i.getSellerId().equals(sellerId)) {
                findItem.add(i);
            }
        }
        return findItem;
    }

    public Item findById(long itemId) {
        return items.get(itemId);
    }

    public void update(Long itemId, Item updateParam) {
        Item updateItem = findById(itemId);
        updateItem.setItemName(updateParam.getItemName());
        updateItem.setPrice(updateParam.getPrice());
        updateItem.setQuantity(updateParam.getQuantity());
        updateItem.setDescription(updateParam.getDescription());
        updateItem.setImage(updateParam.getImage());
    }

    public void clearStore() {
        items.clear();
    }
}
