package blog.devjay.logistics.dto.item;

import lombok.Data;

@Data
public class UpdateItemDTO {
    private Long id;
    private Long warehouseId;
    private String name;
    private Integer price;
    private Integer quantity;
}
