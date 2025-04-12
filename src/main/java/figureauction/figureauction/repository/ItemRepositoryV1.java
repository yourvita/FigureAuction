package figureauction.figureauction.repository;

import figureauction.figureauction.domain.Item;
import figureauction.figureauction.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
}
