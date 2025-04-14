package figureauction.figureauction.service;

import figureauction.figureauction.domain.Member;
import figureauction.figureauction.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository repository;

    public List<Member> memberList() {
        return repository.memberList();
    }
}
