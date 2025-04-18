package figureauction.figureauction;

import figureauction.figureauction.config.AuctionConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(AuctionConfig.class)
@SpringBootApplication
@MapperScan("figureauction.figureauction.mapper")
public class FigureauctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FigureauctionApplication.class, args);
	}

}
