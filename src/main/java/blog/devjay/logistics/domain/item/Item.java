package blog.devjay.logistics.domain.item;

import java.time.LocalDateTime;

public class Item {
    private Long id;
    private Long warehouseId;
    private int price;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}