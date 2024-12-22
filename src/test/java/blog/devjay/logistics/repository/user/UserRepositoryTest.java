package blog.devjay.logistics.repository.user;

import blog.devjay.logistics.domain.user.Role;
import blog.devjay.logistics.domain.user.User;
import blog.devjay.logistics.dto.SearchUserDTO;
import blog.devjay.logistics.dto.UpdateUserDTO;
import blog.devjay.logistics.web.utils.BcryptUtils;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Slf4j
class UserRepositoryTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 정보가 정상적으로 저장되어야 한다.")
    void save() {
        // GIVEN
        User user = createUser("t1");

        // WHEN
        Long id = userRepository.save(user);

        // THEN
        Optional<User> optionalUser = userRepository.findById(id);

        Assertions.assertThat(optionalUser).isPresent();
    }

    @Test
    @DisplayName("이미 사용중인 유저네임의 경우 예외를 던져야한다.")
    void saveException() {
        // GIVEN
        User user1 = createUser("t1");
        User user2 = createUser("t1");

        // WHEN
        userRepository.save(user1);

        Assertions.assertThatThrownBy(() -> {
            userRepository.save(user2);
        }).isInstanceOf(DuplicateKeyException.class);
    }

    @Test
    @DisplayName("유저 아이디로 조회가 되어야 한다.")
    void findById() {
        // GIVEN
        User user = createUser("t1");
        Long id = userRepository.save(user);

        // WHEN
        Optional<User> findUser = userRepository.findById(id);

        // THEN
        Assertions.assertThat(findUser.get().getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("유저네임으로 조회가 되어야 한다.")
    void findByUsername() {
        // GIVEN
        User user = createUser("t1");
        userRepository.save(user);

        // WHEN
        User findUser = userRepository.findByUsername("t1").get();

        // THEN
        Assertions.assertThat(findUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    @DisplayName("모든 유저가 리스트로 반환되어야 한다.")
    void findAll() {
        // GIVEN
        User user1 = createUser("t1");
        User user2 = createUser("t2");
        userRepository.save(user1);
        userRepository.save(user2);

        SearchUserDTO searchUserDTO = new SearchUserDTO();

        // WHEN
        List<User> users = userRepository.findAll(searchUserDTO);

        // THEN
        Assertions.assertThat(users.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("특정 조건으로 검색 시 해당하는 유저만 리스트로 반환되어야 한다.")
    void findAllWithCondition() {
        // GIVEN
        User user1 = createUser("t1");
        User user2 = createUser("t2");
        user1.setRole(Role.ADMIN);
        userRepository.save(user1);
        userRepository.save(user2);

        SearchUserDTO searchUserDTO = new SearchUserDTO();
        searchUserDTO.setRole(Role.ADMIN);

        // WHEN
        List<User> users = userRepository.findAll(searchUserDTO);

        // THEN
        Assertions.assertThat(users.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("유저네임으로 검색 시 대소문자 구분 없이 유저네임이 포함된 유저만 리스트로 반환되어야 한다.")
    void findAllWithUsername() {
        // GIVEN
        User user1 = createUser("poe");
        User user2 = createUser("poegamer");
        User user3 = createUser("pos");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        SearchUserDTO searchUserDTO = new SearchUserDTO();
        searchUserDTO.setUsername("poe");

        // WHEN
        List<User> users = userRepository.findAll(searchUserDTO);

        // THEN
        Assertions.assertThat(users.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("조건 검색 시 대소문자 구분 없이 유저네임이 포함된 유저만 카운트하여 정수로 반환되어야 한다.")
    void findAllCount() {
        // GIVEN
        User user1 = createUser("poe");
        User user2 = createUser("poegamer");
        User user3 = createUser("pos");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        SearchUserDTO searchUserDTO = new SearchUserDTO();
        searchUserDTO.setUsername("poe");

        // WHEN
        int count = userRepository.findAllCount(searchUserDTO);

        // THEN
        Assertions.assertThat(count).isEqualTo(2);
    }

    @Test
    @DisplayName("유저 정보를 업데이트하면 정상적으로 반영되어야 한다.")
    void update() {
        // GIVEN
        User user = createUser("t1");
        Long id = userRepository.save(user);
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setId(id);
        updateUserDTO.setRole(Role.ADMIN);

        // WHEN
        userRepository.update(updateUserDTO);
        User findUser = userRepository.findById(id).get();

        // THEN
        Assertions.assertThat(findUser.getRole()).isEqualTo(Role.ADMIN);
    }

    @Test
    @DisplayName("유저 정보를 업데이트하면 updatedAt이 업데이트 시점의 시간으로 업데이트 되어야 한다.")
    void updatedAt() {
        // GIVEN
        User user = createUser("t1");
        Long id = userRepository.save(user);
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setId(id);
        updateUserDTO.setRole(Role.ADMIN);

        // WHEN
        userRepository.update(updateUserDTO);
        User findUser = userRepository.findById(id).get();

        // THEN
        Assertions.assertThat(findUser.getUpdatedAt()).isNotNull();
    }

    @Test
    @DisplayName("함수가 호출되면 유저의 최근 접속일이 현재 시간으로 업데이트 되야 한다.")
    void updateRecentLoginAt() {
        // GIVEN
        User user = createUser("t1");
        Long id = userRepository.save(user);

        // WHEN
        userRepository.updateRecentLoginAt(id);
        User findUser = userRepository.findById(id).get();

        // THEN
        Assertions.assertThat(findUser.getRecentLoginAt()).isNotNull();
    }

    private User createUser(String username) {
        return new User(username, BcryptUtils.hashPw(username));
    }
}