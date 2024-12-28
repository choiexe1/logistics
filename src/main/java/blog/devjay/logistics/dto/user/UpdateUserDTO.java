package blog.devjay.logistics.dto.user;

import blog.devjay.logistics.domain.user.Role;
import blog.devjay.logistics.domain.user.UserStatus;
import blog.devjay.logistics.dto.UpdateDTO;
import lombok.Data;

@Data
public class UpdateUserDTO implements UpdateDTO {
    private Role role;
    private UserStatus status;
}
