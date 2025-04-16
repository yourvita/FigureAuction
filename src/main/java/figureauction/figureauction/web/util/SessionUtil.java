package figureauction.figureauction.web.util;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

public class SessionUtil {
    public static void setLoginAttributes(Model model, HttpSession session) {
        String loginMember = (session != null) ? (String)session.getAttribute("memberEmail"): null;
        boolean isLoggedIn = loginMember != null;
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("memberEmail", isLoggedIn ? loginMember : null);
    }
}
