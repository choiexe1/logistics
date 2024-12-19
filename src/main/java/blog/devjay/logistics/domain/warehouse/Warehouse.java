package blog.devjay.logistics.domain.warehouse;

import blog.devjay.logistics.domain.rack.Rack;
import java.util.List;

public class Warehouse {
    private Long id;
    private String name;
    private String location;
    private List<Rack> racks;
}
