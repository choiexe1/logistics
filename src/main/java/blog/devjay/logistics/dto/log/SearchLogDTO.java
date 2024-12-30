package blog.devjay.logistics.dto.log;

import blog.devjay.logistics.dto.AbstractPaginable;
import blog.devjay.logistics.dto.SearchDTO;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchLogDTO extends AbstractPaginable implements SearchDTO {
    private String username;
    private String url;
    private String method;
    private Integer status;
    private LocalDateTime createdAt;
    private String orderBy = "ASC";
}
