package blog.devjay.logistics.dto.warehouse;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateWarehouseDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String location;
}
