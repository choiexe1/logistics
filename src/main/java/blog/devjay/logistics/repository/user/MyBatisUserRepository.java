package blog.devjay.logistics.repository.user;

import blog.devjay.logistics.domain.user.User;
import blog.devjay.logistics.dto.user.SearchUserDTO;
import blog.devjay.logistics.dto.user.UpdateUserDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Primary
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
    public List<User> findAll() {
        return mapper.findAll();
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
    public void update(Long id, UpdateUserDTO updateUserDTO) {
        mapper.update(id, updateUserDTO);
    }

    @Override
    public void delete(Long id) {
        mapper.delete(id);
    }

    @Override
    public void updateRecentLoginAt(Long userId) {
        mapper.updateRecentLoginAt(userId);
    }
}
