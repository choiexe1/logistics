package blog.devjay.logistics.dto.item;

import blog.devjay.logistics.dto.AbstractPaginable;
import blog.devjay.logistics.dto.SearchDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchItemDTO extends AbstractPaginable implements SearchDTO {
    private Long warehouseId;
    private String name;
    private Integer price;
    private String orderBy = "ASC";
}
