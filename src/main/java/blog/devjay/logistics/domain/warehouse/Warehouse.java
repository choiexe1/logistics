package blog.devjay.logistics.domain.warehouse;

import blog.devjay.logistics.domain.rack.Rack;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class Warehouse {
    private Long id;
    private String name;
    private String location;
    private List<Rack> racks;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedAt;

    public Warehouse(String name, String location) {
        this.name = name;
        this.location = location;
        this.racks = new ArrayList<>();
    }
}
