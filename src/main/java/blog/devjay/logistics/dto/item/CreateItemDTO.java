package blog.devjay.logistics.dto.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CreateItemDTO {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_\\p{IsHangul}]+$")
    private String name;

    @Positive
    @NotNull
    private Integer price;

    @PositiveOrZero
    @NotNull
    private Integer quantity;
}