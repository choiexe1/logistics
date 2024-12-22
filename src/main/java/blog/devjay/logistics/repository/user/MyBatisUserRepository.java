package blog.devjay.logistics.repository.user;

import blog.devjay.logistics.domain.user.User;
import blog.devjay.logistics.dto.SearchUserDTO;
import blog.devjay.logistics.dto.UpdateUserDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MyBatisUserRepository implements UserRepository {
    private final UserMapper mapper;

    @Override
    public Long save(User user) {
        mapper.save(user);
        return user.getId();
    }

    @Override
    public Optional<User> findById(Long id) {
        return mapper.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return mapper.findByUsername(username);
    }

    @Override
    public List<User> findAll(SearchUserDTO searchUserDTO) {
        return new ArrayList<>(mapper.findAll(searchUserDTO));
    }

    @Override
    public int findAllCount(SearchUserDTO searchUserDTO) {
        return mapper.findAllCount(searchUserDTO);
    }

    @Override
    public void update(UpdateUserDTO updateUserDTO) {
        mapper.update(updateUserDTO);
    }

    @Override
    public void updateRecentLoginAt(Long userId) {
        mapper.updateRecentLoginAt(userId);
    }
}
