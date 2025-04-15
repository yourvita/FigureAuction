package figureauction.figureauction.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
@NoArgsConstructor
@Getter@Setter
public class Auction {
    private int auctionId; // 경매번호
    private Long itemId; // 상품 ID
    private int startPrice; // 시작 가격
    private int currentPrice; // 현재 가격
    private int bidUnit; // 입찰 단위
    private LocalDateTime startTime; // 시작 시간
    private LocalDateTime endTime; // 종료 시간
    private String itemStatus; // 진행 상황

    public Auction(int auctionId, Long itemId, int startPrice, int currentPrice, int bidUnit, LocalDateTime startTime, LocalDateTime endTime, String itemStatus) {
        this.auctionId = auctionId;
        this.itemId = itemId;
        this.startPrice = startPrice;
        this.currentPrice = currentPrice;
        this.bidUnit = bidUnit;
        this.startTime = startTime;
        this.endTime = endTime;
        this.itemStatus = itemStatus;
    }


}
