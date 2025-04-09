package figureauction.figureauction.web;

import figureauction.figureauction.ItemRepository;
import figureauction.figureauction.domain.Item;
import figureauction.figureauction.repository.ItemRepositoryV1;
import figureauction.figureauction.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepositoryV1 itemRepository;
    private final ItemService itemService;

//    @PostConstruct
//    public void init() {
//        itemRepository.save(new Item("jinseo", "itemA", 10000, 1, "잘 관리되었습니다", "/images/jinseo.png"));
//        itemRepository.save(new Item("seojin", "itemB", 20000, 2, "A급 입니다", "/images/seojin.png"));
//    }

/*    @GetMapping
    public String items(Model model) {
        System.out.println("www");
        List<Item> itemList = itemRepository.findAll();
        System.out.println(itemList.getFirst());
        model.addAttribute("items", itemList);
        return "item/items";
    }*/

    @GetMapping
    public String item(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        return "item/items";
    }

    @GetMapping("/{itemId}")
    public String itemDetail(@PathVariable("itemId") long itemId, Model model) {
        model.addAttribute("item", itemRepository.findAll());
        return "item/item";
    }
}
