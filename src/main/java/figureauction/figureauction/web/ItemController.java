package figureauction.figureauction.web;

import figureauction.figureauction.domain.Item;
import figureauction.figureauction.service.AuctionService;
import figureauction.figureauction.service.ItemService;
import figureauction.figureauction.service.ItemServiceV1;
import figureauction.figureauction.web.util.FileUploadUtil;
import figureauction.figureauction.web.util.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemServiceV1 itemService;
    private final AuctionService auctionService;
    private final FileUploadUtil fileUploadUtil;

    @GetMapping
    public String item(HttpSession session, Model model) {
        SessionUtil.setLoginAttributes(model, session);

        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);

        return "item/items";
    }

    @GetMapping("/{itemId}")
    public String itemDetail(@PathVariable("itemId") long itemId,
                             HttpSession session,
                             Model model) {
        SessionUtil.setLoginAttributes(model, session);
        model.addAttribute("item", itemService.findOne(itemId));
        model.addAttribute("auction", auctionService.findOne(itemId));
//        model.addAttribute("bid", auctionService.findBid(itemId));

        return "item/item";
    }

    @GetMapping("/itemimage/{imageDetail}")
    @ResponseBody
    public Resource downloadImage(@PathVariable("imageDetail") String filename) throws MalformedURLException {
        return fileUploadUtil.loadImage(filename);
    }


    @GetMapping("/add")
    public String addForm(HttpSession session) {
        session.setAttribute("userName", session.getAttribute("userName"));
        return "item/addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute("item") Item item,
                          @RequestParam("imageName") MultipartFile image,
                          RedirectAttributes redirectAttributes) throws IOException {
        String fileName = fileUploadUtil.getImagePath(image);
        item.setImageDetail(fileName);
        Item savedItem = itemService.saveItem(item);
        auctionService.createItemAndAuction(savedItem);

        redirectAttributes.addAttribute("itemId", savedItem.getItemId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/item/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable long itemId, Model model) {
        model.addAttribute("item", itemService.findOne(itemId));
        return "item/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable long itemId, @ModelAttribute Item item,@RequestParam("imageName") MultipartFile image, RedirectAttributes redirectAttributes) throws IOException {
        String fileName = fileUploadUtil.getImagePath(image);
        item.setImageDetail(fileName);
        itemService.update(itemId, item);
        return "redirect:/item/" + itemId;
    }

    @PostMapping("/{itemId}/delete")
    public String delete(@PathVariable long itemId) {
        itemService.deleteItem(itemId);

        return "redirect:/item";
    }

    @GetMapping("/{sellerId}/sellerItems")
    public String sellerItems(@PathVariable String sellerId, Model model, HttpSession session) {
        SessionUtil.setLoginAttributes(model, session);

        List<Item> itemList = itemService.findBySellerId(sellerId);
        model.addAttribute("itemList", itemList);

        return "item/sellerItems";
    }

}
