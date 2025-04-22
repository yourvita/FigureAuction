package figureauction.figureauction.service;

import figureauction.figureauction.domain.Member;
import figureauction.figureauction.repository.AdminRepository;
import figureauction.figureauction.web.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository repository;

    public Page<Member> memberList(int page, int size) {
        int offset = (page - 1) * size;
        List<Member> memberList = repository.memberList(size, offset);
        int total = repository.countMemberList();

        return new PageImpl<>(memberList, PageRequest.of(page-1,size), total);
    }

    public void prepareAdmin(Model model, HttpSession session, int page, int size) {
        SessionUtil.setLoginAttributes(model, session);
        Page<Member> content = memberList(page, size);
        model.addAttribute("content", content.getContent());
        model.addAttribute("totalPages", content.getTotalPages());
        model.addAttribute("currentPage", page);
    }

    public void deleteMember(long userId) {
        repository.deleteMember(userId);
    }
}
