package blog.devjay.logistics.domain.rack;

import blog.devjay.logistics.domain.item.Item;
import blog.devjay.logistics.domain.warehouse.Warehouse;
import java.util.List;
import lombok.Getter;

@Getter
public class Rack {
    private Long id;
    private Warehouse warehouse;
    private int height;
    private int width;
    private List<Item> items;
}
