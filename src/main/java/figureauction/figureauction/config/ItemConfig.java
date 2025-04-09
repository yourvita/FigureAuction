//package figureauction.figureauction.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import figureauction.figureauction.mapper.ItemMapper;
//import figureauction.figureauction.ItemRepository;
//import figureauction.figureauction.repository.ItemRepositoryV1;
//import figureauction.figureauction.service.ItemService;
//import figureauction.figureauction.service.ItemServiceV1;
//import lombok.RequiredArgsConstructor;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//
//import javax.sql.DataSource;
//
//@Configuration
//@RequiredArgsConstructor
//public class ItemConfig {
//    private final ItemMapper itemMapper;
//
//    @Bean
//    public DataSource dataSource() {
//        return new HikariDataSource();  // HikariCP 또는 다른 DataSource 설정
//    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource);
//        return sessionFactoryBean.getObject();
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//    @Bean
//    public ItemService itemService() {
//        return new ItemServiceV1(itemRepository());
//    }
//
//    @Bean
//    public ItemRepository itemRepository() {
//        return new ItemRepositoryV1(itemMapper);
//    }
//
///*    // ItemRepository를 위한 빈 정의
//    @Bean
//    public ItemRepository itemRepository() {
//        return new ItemRepositoryV1(itemMapper);
//    }
//
//    // ItemService를 위한 빈 정의
//    @Bean
//    public ItemService itemService(ItemRepository itemRepository) {
//        return new ItemServiceV1(itemRepository);
//    }*/
//}
