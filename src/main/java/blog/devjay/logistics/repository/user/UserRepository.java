package blog.devjay.logistics.repository.user;

import blog.devjay.logistics.domain.user.User;
import java.util.List;

public interface UserRepository {
    void save(User user);

    User findById(Long id);

    User findByUsername(String username);

    List<User> findAll();
}
