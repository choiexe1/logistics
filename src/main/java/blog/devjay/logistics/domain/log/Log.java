package blog.devjay.logistics.domain.log;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Log {
    private Long id;
    private String username;
    private String url;
    private String method;
    private String parameters;
    private ResponseStatus status;
    private LocalDateTime createdAt;

    public Log() {
    }

    public Log(String username, String url, String method, String parameters, ResponseStatus status) {
        this.username = username;
        this.url = url;
        this.method = method.toUpperCase();
        this.parameters = parameters;
        this.status = status;
    }
}