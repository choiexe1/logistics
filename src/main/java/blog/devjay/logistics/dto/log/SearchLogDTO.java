package blog.devjay.logistics.dto.log;

import blog.devjay.logistics.domain.log.ResponseStatus;
import blog.devjay.logistics.dto.AbstractPaginable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString
public class SearchLogDTO extends AbstractPaginable {
    private String username;
    private String url;
    private String method;
    private ResponseStatus status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String orderBy = "ASC";
}
