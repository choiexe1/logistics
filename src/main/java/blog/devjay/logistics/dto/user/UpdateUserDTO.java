package blog.devjay.logistics.dto.user;

import blog.devjay.logistics.domain.user.Role;
import blog.devjay.logistics.domain.user.UserStatus;
import lombok.Data;

@Data
public class UpdateUserDTO {
    private Long id;
    private Role role;
    private UserStatus status;
}
