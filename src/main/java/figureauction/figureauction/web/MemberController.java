package figureauction.figureauction.web;


import figureauction.figureauction.domain.Member;
import figureauction.figureauction.service.MemberService;
import figureauction.figureauction.service.MemberServiceV1;
import figureauction.figureauction.web.util.SessionUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String login(Member member, BindingResult bindingResult, HttpSession session) {
        if(bindingResult.hasErrors()) {
            return "members/loginForm";
        }
        Member tryLogin = service.loginMember(member.getUserEmail(), member.getUserPw());
        if(tryLogin == null) {
            bindingResult.reject("login.error", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "members/loginForm";
        }

        SessionUtil.setLoginSession(session, tryLogin);

        return "redirect:/item";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        SessionUtil.removeLoginAttributes(session);
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

    @GetMapping("/{userId}/edit")
    public String edit(@PathVariable long userId, Model model,
                       HttpSession session) {;
        SessionUtil.setLoginAttributes(model, session);

        model.addAttribute("member", service.findById(userId));
        log.info("등급: {}", service.findById(userId).getUserGrade());
        return "members/editMember";
    }

    @PostMapping("/{userId}/edit")
    public String editMember(@ModelAttribute Member member, Model model, HttpSession session) {
        SessionUtil.setLoginAttributes(model, session);
        service.updateMember(member);
        if(member.getUserEmail().equals("admin")) {
            return "redirect:/admin";
        }
        model.addAttribute("member", service.findById(member.getUserId()));

        return "members/member";
    }
}
