package blog.devjay.logistics.dto.warehouse;

import blog.devjay.logistics.dto.UpdateDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateWarehouseDTO implements UpdateDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String location;
}