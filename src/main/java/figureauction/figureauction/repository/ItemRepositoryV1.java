package figureauction.figureauction.repository;

import figureauction.figureauction.ItemRepository;
import figureauction.figureauction.domain.Item;
import figureauction.figureauction.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
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
}
