package figureauction.figureauction.repository;

import figureauction.figureauction.domain.Member;

public interface MemberRepository {
    void joinMember(Member member);
    void deleteMember(long userId);
    Member findByEmail(String userEmail);
}
