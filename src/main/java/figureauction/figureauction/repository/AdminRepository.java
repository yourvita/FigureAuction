package figureauction.figureauction.repository;

import figureauction.figureauction.domain.Member;
import figureauction.figureauction.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
public class AdminRepository {
    private final AdminMapper mapper;

    public List<Member> memberList(int page, int size) {
        return mapper.memberList(page, size);
    }

    public int countMemberList() {
        return mapper.countMemberList();
    }

    public List<Member> searchMemberList(String searchName, int limit, int offset) {
        return mapper.searchMemberList(searchName, limit, offset);
    }

    public int countSearchMemberList(String searchName) {
        return mapper.countSearchMemberList(searchName);
    }

    public void deleteMember(Long userId) {
        mapper.deleteMember(userId);
    }
}
