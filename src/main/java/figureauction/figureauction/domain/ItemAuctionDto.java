package figureauction.figureauction.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter@Setter
public class ItemAuctionDto {
    private Item item;
    private Auction auction;
}
