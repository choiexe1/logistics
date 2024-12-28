package blog.devjay.logistics.dto.user;

import blog.devjay.logistics.domain.user.Role;
import blog.devjay.logistics.domain.user.UserStatus;
import blog.devjay.logistics.dto.AbstractPaginable;
import blog.devjay.logistics.dto.SearchDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchUserDTO extends AbstractPaginable implements SearchDTO {
    private String username;
    private UserStatus status;
    private Role role;
}
