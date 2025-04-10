package figureauction.figureauction.mapper;

import figureauction.figureauction.domain.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {
    void saveItem(Item item);
    List<Item> findAll();
    Item findOne(long id);
    void update(@Param("id") long id, @Param("item") Item item);
}
