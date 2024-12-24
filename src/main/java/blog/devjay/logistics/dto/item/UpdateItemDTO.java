package blog.devjay.logistics.dto.item;

import lombok.Data;

@Data
public class UpdateItemDTO {
    private Long id;
    private Long warehouseId;
    private String name;
    private int price;
    private int quantity;
}
