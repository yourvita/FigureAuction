package figureauction.figureauction.repository;

import figureauction.figureauction.domain.Member;
import figureauction.figureauction.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
public class AdminRepository {
    private final AdminMapper mapper;

    public List<Member> memberList() {
        return mapper.memberList();
    }
}
