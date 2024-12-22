package blog.devjay.logistics.dto.warehouse;

import blog.devjay.logistics.domain.rack.Rack;
import java.util.List;
import lombok.Data;

@Data
public class UpdateWarehouseDTO {
    private Long id;
    private String name;
    private String location;
    private List<Rack> racks;
}