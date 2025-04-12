package figureauction.figureauction.web;


import figureauction.figureauction.domain.Member;
import figureauction.figureauction.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String login(Member member, BindingResult bindingResult, HttpServletResponse response) {
        if(bindingResult.hasErrors()) {
            return "members/loginForm";
        }
        Member tryLogin = service.loginMember(member.getUserEmail(), member.getUserPw());
        if(tryLogin == null) {
            bindingResult.reject("login.error", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "members/loginForm";
        }
        Cookie idCookie = new Cookie("memberEmail", tryLogin.getUserEmail());
        response.addCookie(idCookie);
        return "redirect:/item";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response, Model model) {
        System.out.println("로그아웃");
        Cookie cookie = new Cookie("memberEmail", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/";
    }
}
