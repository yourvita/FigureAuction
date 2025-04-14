package figureauction.figureauction.web;


import figureauction.figureauction.domain.Member;
import figureauction.figureauction.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService service;

    @GetMapping
    public String loginForm() {
        return "members/loginForm";
    }

    @PostMapping
    public String login(Member member, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            return "members/loginForm";
        }
        Member tryLogin = service.loginMember(member.getUserEmail(), member.getUserPw());
        if(tryLogin == null) {
            bindingResult.reject("login.error", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "members/loginForm";
        }
        HttpSession session = request.getSession();
        session.setAttribute("memberEmail", tryLogin.getUserEmail());
        session.setAttribute("userName", tryLogin.getUserName());

        return "redirect:/item";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @GetMapping("/joinMember")
    public String joinForm() {
        return "members/joinForm";
    }

    @PostMapping("/joinMember")
    public String joinMember(@ModelAttribute Member member) {
        service.joinMember(member);
        return "redirect:/members";
    }
}
