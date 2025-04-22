package figureauction.figureauction.domain;

import lombok.*;

@NoArgsConstructor
@Getter@Setter
public class ItemAuctionDto {
    private Item item;
    private Auction auction;

    public void setItem(Item item) {
        this.item = item;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
}