package figureauction.figureauction.mapper;

import figureauction.figureauction.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<Member> memberList(@Param("limit") int limit, @Param("offset") int offset);
    int countMemberList();
    void deleteMember(Long userId);
}
