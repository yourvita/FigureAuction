package figureauction.figureauction.config;

import figureauction.figureauction.mapper.AdminMapper;
import figureauction.figureauction.mapper.AuctionMapper;
import figureauction.figureauction.mapper.ItemMapper;
import figureauction.figureauction.mapper.MemberMapper;
import figureauction.figureauction.repository.*;
import figureauction.figureauction.service.*;
import figureauction.figureauction.web.util.NotificationSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
@RequiredArgsConstructor
public class AuctionConfig {
    private final MemberMapper memberMapper;
    private final ItemMapper itemMapper;
    private final AuctionMapper auctionMapper;
    private final AdminMapper adminMapper;

    @Bean
    public MemberService memberService() {
        return new MemberServiceV1(memberRepository());
    }

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public AuctionService auctionService(SimpMessagingTemplate brokerMessagingTemplate) {
        return new AuctionServiceV1(auctionRepository(), itemService(), memberService(), new NotificationSender(brokerMessagingTemplate));
    }

    @Bean
    public AdminService adminService() {
        return new AdminService(adminRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemberRepositoryV1(memberMapper);
    }

    @Bean
    public ItemRepository itemRepository() {
        return new ItemRepositoryV1(itemMapper);
    }

    @Bean
    public AuctionRepository auctionRepository() {
        return new AuctionRepositoryV1(auctionMapper);
    }

    @Bean
    public AdminRepository adminRepository() {
        return new AdminRepository(adminMapper);
    }


}
