package blog.devjay.logistics.domain;

import lombok.Data;

@Data
public abstract class AbstractPaginable {
    private int size = 10;
    private int page = 1;
}
