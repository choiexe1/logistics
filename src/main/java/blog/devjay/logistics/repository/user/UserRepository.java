package blog.devjay.logistics.repository.user;

import blog.devjay.logistics.domain.user.User;
import blog.devjay.logistics.dto.SearchUserDTO;
import blog.devjay.logistics.dto.UpdateUserDTO;
import java.util.List;

public interface UserRepository {
    void save(User user);

    User findById(Long id);

    User findByUsername(String username);

    List<User> findAll(SearchUserDTO searchUserDTO);

    int findAllCount(SearchUserDTO searchUserDTO);

    void updateUser(UpdateUserDTO updateUserDTO);

    void updateRecentLoginAt(Long userId);
}
