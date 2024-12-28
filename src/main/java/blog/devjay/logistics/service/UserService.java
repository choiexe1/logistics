package blog.devjay.logistics.service;

import blog.devjay.logistics.domain.exception.NotFoundException;
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
public class UserService implements IService<User, Long, SearchUserDTO, UpdateUserDTO> {
    private final UserRepository userRepository;

    @Override
    public Long create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        }

        throw new NotFoundException("유저를 찾을 수 없습니다.");
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public List<User> findAll(SearchUserDTO searchUserDTO) {
        return userRepository.findAll(searchUserDTO);
    }
    
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return user.get();
        }

        throw new NotFoundException("유저를 찾을 수 없습니다.");
    }

    @Override
    public int findAllCount(SearchUserDTO searchUserDTO) {
        return userRepository.findAllCount(searchUserDTO);
    }

    @Override
    public void update(Long id, UpdateUserDTO updateUserDTO) {
        userRepository.update(id, updateUserDTO);
    }

    public void updateRecentLoginAt(Long userId) {
        userRepository.updateRecentLoginAt(userId);
    }
}
