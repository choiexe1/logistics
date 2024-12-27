package blog.devjay.logistics.dto.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CreateItemDTO {
    @NotBlank
    private String name;

    @Positive
    private Integer price;

    @PositiveOrZero
    private Integer quantity;
}