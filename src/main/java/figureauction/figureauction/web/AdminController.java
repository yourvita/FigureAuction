package figureauction.figureauction.web;

import figureauction.figureauction.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService service;

    @GetMapping
    public String admin(HttpServletRequest request) {
        request.getSession().setAttribute("adminAccess", true);
        return "redirect:/admin/adminPage";
    }

    @GetMapping("/adminPage")
    public String adminPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Boolean allowed = (session != null) ? (Boolean) session.getAttribute("adminAccess") : null;
        if(allowed == null || !allowed) {
            log.warn("Admin access is denied");
            return "redirect:/";
        }

        session.removeAttribute("adminAccess");
        isLoginCheck(model, session);
        model.addAttribute("memberList",service.memberList());

        return "admin/admin";
    }

    private static void isLoginCheck(Model model, HttpSession session) {
        String loginMember = (session != null) ? (String) session.getAttribute("memberEmail") : null;
        boolean isLoggedIn = loginMember != null;
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("memberEmail", isLoggedIn ? loginMember : null);
    }
}
