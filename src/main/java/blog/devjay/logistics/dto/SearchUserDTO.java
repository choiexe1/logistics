package blog.devjay.logistics.dto;

import blog.devjay.logistics.domain.user.Role;
import blog.devjay.logistics.domain.user.UserStatus;
import lombok.Data;

@Data
public class SearchUserDTO {
    private String username;
    private UserStatus status;
    private Role role;
    private int size = 10;
    private int page = 1;
}
