package blog.devjay.logistics.domain.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String username;
    private String password;
    private Role role;
    private UserStatus status;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = Role.USER;
        this.status = UserStatus.ACTIVATE;
    }
}
