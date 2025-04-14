package figureauction.figureauction.service;

import figureauction.figureauction.domain.Member;

public interface MemberService {
    void joinMember(Member member);
    void deleteMember(long userId);
    Member loginMember(String userEmail, String userPw);
    Member findById(long userId);
    void updateMember(Member member);
}
