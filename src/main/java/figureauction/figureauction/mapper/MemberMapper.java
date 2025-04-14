package figureauction.figureauction.mapper;

import figureauction.figureauction.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void joinMember(Member member);
    void deleteMember(long memberId);
    Member findByEmail(String memberEmail);
    Member findById(long memberId);
    void updateMember(Member member);
}
