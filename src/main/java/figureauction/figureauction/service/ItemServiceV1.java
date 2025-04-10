package figureauction.figureauction.service;

import figureauction.figureauction.ItemRepository;
import figureauction.figureauction.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceV1 implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item saveItem(Item item) {
        return itemRepository.saveItem(item);
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item findOne(long id) {
        return itemRepository.findOne(id);
    }

    @Override
    public void update(long id, Item item) {
        itemRepository.update(id, item);
    }
}
