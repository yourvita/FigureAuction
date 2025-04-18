package figureauction.figureauction.service;

import figureauction.figureauction.domain.Member;
import figureauction.figureauction.repository.AdminRepository;
import figureauction.figureauction.web.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository repository;

    public List<Member> memberList() {
        return repository.memberList();
    }

    public void prepareAdmin(Model model, HttpSession session) {
        SessionUtil.setLoginAttributes(model, session);
        model.addAttribute("memberList", memberList());
    }
}
