package blog.devjay.logistics.domain.item;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private Long id;
    private Long warehouseId;
    private String name;
    private int price;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Item() {
    }

    public Item(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}