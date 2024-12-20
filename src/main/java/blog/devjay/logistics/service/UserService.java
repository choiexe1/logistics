package blog.devjay.logistics.service;

import blog.devjay.logistics.domain.exception.UserNotFoundException;
import blog.devjay.logistics.domain.user.User;
import blog.devjay.logistics.dto.SearchUserDTO;
import blog.devjay.logistics.dto.UpdateUserDTO;
import blog.devjay.logistics.repository.user.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public void register(User user) {
        userRepository.save(user);
    }

    public User findById(Long id) {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

    public List<User> findAll(SearchUserDTO searchUserDTO) {
        return userRepository.findAll(searchUserDTO);
    }

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

    public int findAllCount(SearchUserDTO searchUserDTO) {
        return userRepository.findAllCount(searchUserDTO);
    }

    public void updateUser(UpdateUserDTO updateUserDTO) {
        userRepository.updateUser(updateUserDTO);
    }

    public void updateRecentLoginAt(Long userId) {
        userRepository.updateRecentLoginAt(userId);
    }
}
