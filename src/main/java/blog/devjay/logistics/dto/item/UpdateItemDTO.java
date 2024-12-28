package blog.devjay.logistics.dto.item;

import blog.devjay.logistics.dto.UpdateDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class UpdateItemDTO implements UpdateDTO {
    @NotNull
    private Long warehouseId;

    @NotBlank
    private String name;

    @Positive
    @NotNull
    private Integer price;

    @PositiveOrZero
    @NotNull
    private Integer quantity;
}
