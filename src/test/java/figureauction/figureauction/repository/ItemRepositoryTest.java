/*
package figureauction.figureauction.repository;

import figureauction.figureauction.domain.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

//    @AfterEach
//    void afterEach() {
//        itemRepository.clearStore();
//    }

    @Test
    void save() {
        Item item = new Item("seller", "itemA", 20000, 2, "description", "image");

        Item savedItem = itemRepository.save(item);
        Item findItem = itemRepository.findById(item.getItemId());
        assertEquals(savedItem.getItemId(),findItem.getItemId());
    }

    @Test
    void findAll() {
        Item itemA = new Item("seller", "itemA", 20000, 2, "description", "image");
        Item itemB = new Item("seller2", "itemB", 30000, 3, "description", "image");

        itemRepository.save(itemA);
        itemRepository.save(itemB);
        List<Item> items = itemRepository.findAll();
        assertEquals(2, items.size());
        assertThat(items).contains(itemA, itemB);
    }

    @Test
    void findByItemName() {
        Item itemA = new Item("seller", "itemA", 20000, 2, "description", "image");
        Item itemB = new Item("seller", "itemB", 30000, 3, "description", "image");
        Item itemC = new Item("finder", "itemC", 30000, 3, "description", "image");
        itemRepository.save(itemA);
        itemRepository.save(itemB);
        itemRepository.save(itemC);

        List<Item> findItem = itemRepository.findByItemName("itemA");

        assertEquals(1, findItem.size());
        assertThat(findItem).contains(itemA);
    }

    @Test
    void findBySellerId() {
        Item itemA = new Item("seller", "itemA", 20000, 2, "description", "image");
        Item itemB = new Item("seller2", "itemB", 30000, 3, "description", "image");
        Item itemC = new Item("seller", "itemC", 30000, 3, "description", "image");

        itemRepository.save(itemA);
        itemRepository.save(itemB);
        itemRepository.save(itemC);

        List<Item> findItem = itemRepository.findBySellerId("seller");

        assertEquals(2, findItem.size());
        assertThat(findItem).contains(itemA, itemC);
    }

    @Test
    void update() {
        Item itemA = new Item("seller", "itemA", 20000, 2, "description", "image");

        itemRepository.save(itemA);

        Item updateItem = new Item("seller", "itemA", 30000, 3, "description", "image");
        itemRepository.update(itemA.getItemId(), updateItem);

        assertEquals(30000, updateItem.getPrice());
    }
}*/
