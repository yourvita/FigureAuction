package figureauction.figureauction.mapper;

import figureauction.figureauction.domain.Member;

public interface MemberMapper {
    void joinMember(Member member);
    void deleteMember(long memberId);
    Member findByEmail(String memberEmail);
}
