package figureauction.figureauction.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Getter @Setter
public class Item {

    private Long itemId; // 아이템고유넘버(사용자 설정x)
    private String sellerId; // 판매자 ID
    private String userEmail; // 판매자 Email
    private String itemName; // 판매아이템이름
    private int price; // 가격
    private int quantity; // 수량
    private String descriptionDetail; // 설명
    private String imageDetail; // 이미지
    private LocalDateTime regDate;

    public Item(Long itemId, String sellerId, String userEmail, String itemName, int price, int quantity, String descriptionDetail, String imageDetail, LocalDateTime regDate) {
        this.itemId = itemId;
        this.sellerId = sellerId;
        this.userEmail = userEmail;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.descriptionDetail = descriptionDetail;
        this.imageDetail = imageDetail;
        this.regDate = regDate;
    }

    public Item(Long itemId, String sellerId, String userEmail, String itemName, int price, int quantity, String descriptionDetail, String imageDetail) {
        this.itemId = itemId;
        this.sellerId = sellerId;
        this.userEmail = userEmail;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.descriptionDetail = descriptionDetail;
        this.imageDetail = imageDetail;
    }

    public Item(Long itemId, String sellerId, String itemName, int price, int quantity, String descriptionDetail, String imageDetail) {
        this.itemId = itemId;
        this.sellerId = sellerId;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.descriptionDetail = descriptionDetail;
        this.imageDetail = imageDetail;
    }

    public Item(String sellerId, String itemName, int price, int quantity, String descriptionDetail, String imageDetail) {
        this.sellerId = sellerId;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.descriptionDetail = descriptionDetail;
        this.imageDetail = imageDetail;
    }

    public Item(String itemName, int price, int quantity, String descriptionDetail, String imageDetail) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.descriptionDetail = descriptionDetail;
        this.imageDetail = imageDetail;
    }

    public Item(String itemName, int price, int quantity, String descriptionDetail) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.descriptionDetail = descriptionDetail;
    }
}
