package figureauction.figureauction.web.util;

import figureauction.figureauction.domain.Member;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

public class SessionUtil {
    public static void setLoginAttributes(Model model, HttpSession session) {
        String loginMember = (session != null) ? (String)session.getAttribute("memberEmail"): null;
        boolean isLoggedIn = loginMember != null;
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("memberEmail", isLoggedIn ? loginMember : null);
    }

    public static void setLoginSession(HttpSession session, Member member) {
        session.setAttribute("memberEmail", member.getUserEmail());
        session.setAttribute("userEmail", member.getUserEmail());
        session.setAttribute("userId", member.getUserId());
        session.setAttribute("userName", member.getUserName());
    }

    public static void removeLoginAttributes(HttpSession session) {
        if(session != null) {
            session.invalidate();
        }
    }
}
