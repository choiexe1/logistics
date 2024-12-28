package blog.devjay.logistics.dto.warehouse;

import blog.devjay.logistics.dto.AbstractPaginable;
import blog.devjay.logistics.dto.SearchDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchWarehouseDTO extends AbstractPaginable implements SearchDTO {
    private String name;
    private String location;
}
