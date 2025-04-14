package figureauction.figureauction.service;

import figureauction.figureauction.domain.Member;
import figureauction.figureauction.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceV1 implements MemberService {
    private final MemberRepository repository;

    @Override
    public void joinMember(Member member) {
        repository.joinMember(member);
    }

    @Override
    public void deleteMember(long userId) {

    }

    @Override
    public Member loginMember(String userEmail, String userPw) {
        Member loginMember = repository.findByEmail(userEmail);
        if(loginMember != null) {
            if(loginMember.getUserPw().equals(userPw)) {
                return loginMember;
            }
        }
        return null;
    }

    @Override
    public Member findById(long userId) {
        return repository.findById(userId);
    }

    @Override
    public void updateMember(Member member) {
        repository.updateMember(member);
    }
}
