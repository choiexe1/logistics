package blog.devjay.logistics.repository.user;

import blog.devjay.logistics.domain.user.User;
import blog.devjay.logistics.dto.user.SearchUserDTO;
import blog.devjay.logistics.dto.user.UpdateUserDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends UserRepository {
    @Override
    Optional<User> findByUsername(String username);

    @Override
    void updateRecentLoginAt(Long userId);

    @Override
    Long save(User user);

    @Override
    Optional<User> findById(Long id);

    @Override
    List<User> findAll();

    @Override
    List<User> findAll(SearchUserDTO searchUserDTO);

    @Override
    int findAllCount(SearchUserDTO searchUserDTO);

    @Override
    void update(Long id, UpdateUserDTO updateUserDTO);

    @Override
    void delete(Long id);
}
