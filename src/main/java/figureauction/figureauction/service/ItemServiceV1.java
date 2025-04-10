package figureauction.figureauction.service;

import figureauction.figureauction.ItemRepository;
import figureauction.figureauction.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
