package figureauction.figureauction.mapper;

import figureauction.figureauction.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<Member> memberList();
    void deleteMember(Long userId);
}
