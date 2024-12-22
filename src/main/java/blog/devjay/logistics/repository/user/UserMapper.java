package blog.devjay.logistics.repository.user;

import blog.devjay.logistics.domain.user.User;
import blog.devjay.logistics.dto.user.SearchUserDTO;
import blog.devjay.logistics.dto.user.UpdateUserDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends UserRepository {
    Long save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    List<User> findAll(SearchUserDTO searchUserDTO);

    int findAllCount(SearchUserDTO searchUserDTO);

    void update(UpdateUserDTO updateUserDTO);

    void updateRecentLoginAt(Long userId);
}
