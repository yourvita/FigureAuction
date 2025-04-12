package figureauction.figureauction.web;

import figureauction.figureauction.domain.Item;
import figureauction.figureauction.domain.Member;
import figureauction.figureauction.service.ItemService;
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
    private final ItemService itemService;
    private final String uploadDir = "C:\\Users\\wlstj\\spring\\figureauction\\src\\main\\resources\\itemimage\\";


    @GetMapping
    public String item(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();

        isLoginCheck(model, session);

        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);

        return "item/items";
    }

    @GetMapping("/{itemId}")
    public String itemDetail(@PathVariable("itemId") long itemId,
                             HttpServletRequest request,
                             Model model) {
        HttpSession session = request.getSession();
        isLoginCheck(model, session);
        model.addAttribute("item", itemService.findOne(itemId));
        return "item/item";
    }

    @GetMapping("/itemimage/{imageDetail}")
    @ResponseBody
    public Resource downloadImage(@PathVariable("imageDetail") String filename) throws MalformedURLException {
        UrlResource resource;

        try{
            resource = new UrlResource("file:" + uploadDir + filename);
            if(!resource.exists() || !resource.isReadable()) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            resource = new UrlResource("file:" + uploadDir + "default.png");
        }

        return resource;
    }


    @GetMapping("/add")
    public String addForm(Model model) {
        return "item/addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute("item") Item item,@RequestParam("imageName") MultipartFile image, RedirectAttributes redirectAttributes) throws IOException {
        String fileName = getImagePath(image);
        item.setImageDetail(fileName);
        Item savedItem = itemService.saveItem(item);
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
        String fileName = getImagePath(image);
        item.setImageDetail(fileName);
        itemService.update(itemId, item);
        return "redirect:/item/" + itemId;
    }

    private String getImagePath(MultipartFile image) throws IOException {
        String fileName = null;
        if(!image.isEmpty()){
            fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            File save = new File(uploadDir + fileName);
            image.transferTo(save);
        }
        return fileName;
    }

    private static void isLoginCheck(Model model, HttpSession session) {
        String loginMember = (session != null) ? (String) session.getAttribute("memberEmail") : null;
        boolean isLoggedIn = loginMember != null;
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("memberEmail", isLoggedIn ? loginMember : null);
    }
}
