package blog.devjay.logistics.dto.item;

import blog.devjay.logistics.dto.AbstractPaginable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchItemDTO extends AbstractPaginable {
    private Long warehouseId;
    private String name;
    private Integer price;
    private String orderBy = "ASC";
}
