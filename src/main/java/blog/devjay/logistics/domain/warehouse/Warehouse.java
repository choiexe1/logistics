package blog.devjay.logistics.domain.warehouse;

import blog.devjay.logistics.domain.rack.Rack;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Warehouse {
    private Long id;
    private String name;
    private String location;
    private List<Rack> racks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Warehouse(String name, String location) {
        this.name = name;
        this.location = location;
        this.racks = new ArrayList<>();
    }
}
