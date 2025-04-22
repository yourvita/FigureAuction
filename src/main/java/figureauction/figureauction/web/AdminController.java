package figureauction.figureauction.web;

import figureauction.figureauction.service.AdminService;
import figureauction.figureauction.web.util.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService service;

    @GetMapping
    public String admin(HttpSession session) {
        session.setAttribute("adminAccess", true);
        return "redirect:/admin/adminPage";
    }

    @GetMapping("/adminPage")
    public String adminPage(Model model, HttpSession session) {
        Boolean allowed = (session != null) ? (Boolean) session.getAttribute("adminAccess") : null;
        if (allowed == null || !allowed) {
            log.warn("Admin access is denied");
            return "redirect:/";
        }

        service.prepareAdmin(model, session);

        return "admin/admin";
    }

    @PostMapping("/{userId}/deleteMember")
    public String deleteMember(@PathVariable Long userId) {
        service.deleteMember(userId);

        return "admin/admin";
    }
}
