package blog.devjay.logistics.dto.warehouse;

import blog.devjay.logistics.domain.AbstractPaginable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchWarehouseDTO extends AbstractPaginable {
    private String name;
    private String location;
}