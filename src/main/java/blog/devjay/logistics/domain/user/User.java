package blog.devjay.logistics.domain.user;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class User {
    private Long id;
    private String username;
    private String password;
    private Role role;
    private UserStatus status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recentLoginAt;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = Role.USER;
        this.status = UserStatus.ACTIVATE;
    }
}
