package figureauction.figureauction.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class Bid {
    private int bidId; // 입찰 ID
    private int auctionId; // 경매번호
    private Long bidderId; // TODO 입찰자 ID -> ID를 통해서 닉네임 보여줘야함
    private int bidPrice;
    private Date bidTime;

    public Bid(int bidId, int auctionId, Long bidderId, int bidPrice, Date bidTime) {
        this.bidId = bidId;
        this.auctionId = auctionId;
        this.bidderId = bidderId;
        this.bidPrice = bidPrice;
        this.bidTime = bidTime;
    }
}
