package figureauction.figureauction.web;

import figureauction.figureauction.domain.Item;
import figureauction.figureauction.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("jinseo", "itemA", 10000, 1, "잘 관리되었습니다", "/images/jinseo.png"));
        itemRepository.save(new Item("seojin", "itemB", 20000, 2, "A급 입니다", "/images/seojin.png"));
    }

    @GetMapping
    public String items(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "item/items";
    }

    @GetMapping("/{itemId}")
    public String itemDetail(@PathVariable("itemId") long itemId, Model model) {
        model.addAttribute("item", itemRepository.findById(itemId));
        return "item/item";
    }
}
