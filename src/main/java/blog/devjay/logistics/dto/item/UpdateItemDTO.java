package blog.devjay.logistics.dto.item;

import blog.devjay.logistics.dto.UpdateDTO;
import lombok.Data;

@Data
public class UpdateItemDTO implements UpdateDTO {
    private Long id;
    private Long warehouseId;
    private String name;
    private Integer price;
    private Integer quantity;
}
