package figureauction.figureauction.web;

import figureauction.figureauction.ItemRepository;
import figureauction.figureauction.domain.Item;
import figureauction.figureauction.repository.ItemRepositoryV1;
import figureauction.figureauction.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepositoryV1 itemRepository;
    private final ItemService itemService;

    @GetMapping
    public String item(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        return "item/items";
    }

    @GetMapping("/{itemId}")
    public String itemDetail(@PathVariable("itemId") long itemId, Model model) {
        model.addAttribute("item", itemService.findOne(itemId));
        return "item/item";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable long itemId, Model model) {
        model.addAttribute("item", itemService.findOne(itemId));
        return "item/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable long itemId, @ModelAttribute Item item) {
        itemService.update(itemId, item);
        return "redirect:/item/" + itemId;
    }
}
