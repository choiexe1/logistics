package blog.devjay.logistics.repository.user;

import blog.devjay.logistics.domain.user.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryUserRepository implements UserRepository {
    private static final Map<Long, User> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public void save(User user) {
        User exist = findByUsername(user.getUsername());
        if (exist != null) {
            throw new IllegalArgumentException("이미 사용중인 유저네임입니다. username = " + user.getUsername());
        }

        user.setId(++sequence);
        store.put(sequence, user);
    }

    @Override
    public User findById(Long id) {
        return store.get(id);
    }

    @Override
    public User findByUsername(String username) {
        return store.values().stream()
                .filter((user -> user.getUsername().equals(username)))
                .findFirst().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }
}
