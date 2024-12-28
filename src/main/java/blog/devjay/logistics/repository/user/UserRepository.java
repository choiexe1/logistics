package blog.devjay.logistics.repository.user;

import blog.devjay.logistics.domain.user.User;
import blog.devjay.logistics.dto.user.SearchUserDTO;
import blog.devjay.logistics.dto.user.UpdateUserDTO;
import blog.devjay.logistics.repository.IRepository;
import java.util.Optional;

public interface UserRepository extends IRepository<User, Long, SearchUserDTO, UpdateUserDTO> {
    Optional<User> findByUsername(String username);

    void updateRecentLoginAt(Long userId);
}
