package figureauction.figureauction.mapper;

import figureauction.figureauction.domain.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    void saveItem(Item item);
    List<Item> findAll();
}
