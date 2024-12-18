package blog.devjay.logistics.repository.user;

import blog.devjay.logistics.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void save(User user);

    User findById(Long id);

    User findByUsername(String username);

//    List<User> findAll();
}
