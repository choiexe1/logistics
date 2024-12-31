package blog.devjay.logistics.dto.warehouse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateWarehouseDTO {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_\\p{IsHangul}]+$")
    private String name;
    @NotBlank
    private String location;
}
