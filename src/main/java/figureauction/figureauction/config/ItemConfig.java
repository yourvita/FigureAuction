package figureauction.figureauction.config;

import com.zaxxer.hikari.HikariDataSource;
import figureauction.figureauction.mapper.ItemMapper;
import figureauction.figureauction.repository.ItemRepository;
import figureauction.figureauction.repository.ItemRepositoryV1;
import figureauction.figureauction.web.ItemController;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class ItemConfig {
    private final ItemMapper itemMapper;

    @Bean
    public ItemRepository itemRepositoryService() {
        return new ItemRepositoryV1(itemRepository());
    }

    @Bean
    public ItemMapper itemRepository() {
        return new ItemRepositoryV1(itemMapper);
    }
}
