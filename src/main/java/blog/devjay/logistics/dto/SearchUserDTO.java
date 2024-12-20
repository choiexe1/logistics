package blog.devjay.logistics.dto;

import blog.devjay.logistics.domain.AbstractPaginable;
import blog.devjay.logistics.domain.user.Role;
import blog.devjay.logistics.domain.user.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchUserDTO extends AbstractPaginable {
    private String username;
    private UserStatus status;
    private Role role;
}
