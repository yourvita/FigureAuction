package figureauction.figureauction.service;

import figureauction.figureauction.repository.ItemRepository;
import figureauction.figureauction.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceV1 implements ItemService {

    private final ItemRepository itemRepository;
    private final String uploadDir = "C:\\Users\\wlstj\\spring\\figureauction\\src\\main\\resources\\itemimage";
    @Override
    public Item saveItem(Item item) {
//        String imageDetail = item.getImageDetail();
//        String uniqueName = UUID.randomUUID() + "_" + imageDetail;
//        File saveFile = new File(uploadDir, uniqueName);
//        image.transferTo(saveFile);
//        item.setImageDetail(imageDetail);

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
