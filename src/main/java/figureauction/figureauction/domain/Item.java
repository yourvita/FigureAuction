package figureauction.figureauction.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class Item {

    private Long itemId; // 아이템고유넘버(사용자 설정x)
    private String sellerId; // 판매자 ID
    private String itemName; // 판매아이템이름
    private int price; // 가격
    private int quantity; // 수량
    private String description; // 설명
    private String image; // 이미지

    public Item(String sellerId, String itemName, int price, int quantity, String description, String image) {
        this.sellerId = sellerId;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.image = image;
    }
}
