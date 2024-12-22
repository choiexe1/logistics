package blog.devjay.logistics.service;

import blog.devjay.logistics.domain.exception.UserNotFoundException;
import blog.devjay.logistics.domain.user.User;
import blog.devjay.logistics.dto.user.SearchUserDTO;
import blog.devjay.logistics.dto.user.UpdateUserDTO;
import blog.devjay.logistics.repository.user.UserRepository;
import java.util.List;
import java.util.Optional;
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

    public Long register(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        }

        throw new UserNotFoundException();
    }

    public List<User> findAll(SearchUserDTO searchUserDTO) {
        return userRepository.findAll(searchUserDTO);
    }

    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return user.get();
        }

        throw new UserNotFoundException();
    }

    public int findAllCount(SearchUserDTO searchUserDTO) {
        return userRepository.findAllCount(searchUserDTO);
    }

    public void updateUser(UpdateUserDTO updateUserDTO) {
        userRepository.update(updateUserDTO);
    }

    public void updateRecentLoginAt(Long userId) {
        userRepository.updateRecentLoginAt(userId);
    }
}
