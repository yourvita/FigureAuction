package figureauction.figureauction.service;

import figureauction.figureauction.domain.Member;
import figureauction.figureauction.repository.AdminRepository;
import figureauction.figureauction.web.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository repository;

    public void prepareAdmin(Model model, HttpSession session, int page, int size) {
        SessionUtil.setLoginAttributes(model, session);
        int offset = (page - 1) * size;
        List<Member> memberList = repository.memberList(size, offset);
        int total = repository.countMemberList();

        Page<Member> content = new PageImpl<>(memberList, PageRequest.of(page-1,size), total);
        model.addAttribute("content", content.getContent());
        model.addAttribute("totalPages", content.getTotalPages());
        model.addAttribute("currentPage", page);

    }

    public void searchMemberList(Model model,
                                         HttpSession session,
                                         String searchName, int page, int size) {
        SessionUtil.setLoginAttributes(model, session);
        int offset = (page - 1) * size;
        List<Member> memberList = repository.searchMemberList(searchName, size, offset);
        int total = repository.countSearchMemberList(searchName);
        PageImpl<Member> searchMember = new PageImpl<>(memberList, PageRequest.of(page - 1, size), total);
        model.addAttribute("content", searchMember.getContent());
        model.addAttribute("totalPages", searchMember.getTotalPages());
        model.addAttribute("currentPage", page);
    }

    public void deleteMember(long userId) {
        repository.deleteMember(userId);
    }
}
