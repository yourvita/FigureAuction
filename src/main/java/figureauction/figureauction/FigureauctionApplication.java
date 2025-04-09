package figureauction.figureauction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//@Import(ItemConfig.class)
@SpringBootApplication
@MapperScan("figureauction.figureauction.mapper")
public class FigureauctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FigureauctionApplication.class, args);
	}

}
