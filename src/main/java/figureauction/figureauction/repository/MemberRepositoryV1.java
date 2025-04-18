package figureauction.figureauction.repository;

import figureauction.figureauction.domain.Member;
import figureauction.figureauction.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
public class MemberRepositoryV1 implements MemberRepository {

    private final MemberMapper mapper;
    @Override
    public void joinMember(Member member) {
        mapper.joinMember(member);
    }

    @Override
    public void deleteMember(long userId) {

    }

    @Override
    public Member findByEmail(String userEmail) {
        return mapper.findByEmail(userEmail);
    }

    @Override
    public Member findById(long userId) {
        return mapper.findById(userId);
    }

    @Override
    public void updateMember(Member member) {
        mapper.updateMember(member);
    }


}
