package blog.devjay.logistics.repository.user;

import blog.devjay.logistics.domain.user.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MyBatisUserRepository implements UserRepository {
    private final UserMapper mapper;

    @Override
    public void save(User user) {
        mapper.save(user);
    }

    @Override
    public User findById(Long id) {
        return mapper.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return mapper.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }
}
