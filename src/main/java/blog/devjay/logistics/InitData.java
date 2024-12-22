package blog.devjay.logistics;

import blog.devjay.logistics.domain.user.Role;
import blog.devjay.logistics.domain.user.User;
import blog.devjay.logistics.domain.user.UserStatus;
import blog.devjay.logistics.domain.warehouse.Warehouse;
import blog.devjay.logistics.repository.user.UserRepository;
import blog.devjay.logistics.repository.warehouse.WarehouseRepository;
import blog.devjay.logistics.web.utils.BcryptUtils;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class InitData {
    private final UserRepository userRepository;
    private final WarehouseRepository warehouseRepository;

    @EventListener(ApplicationReadyEvent.class)
    void initUsers() {
        User user = new User("user", BcryptUtils.hashPw("user"));
        User editor = new User("editor", BcryptUtils.hashPw("editor"));
        User admin = new User("admin", BcryptUtils.hashPw("admin"));
        User test = new User("test", BcryptUtils.hashPw("test"));

        editor.setRole(Role.EDITOR);
        admin.setRole(Role.ADMIN);
        test.setRole(Role.ADMIN);

        userRepository.save(user);
        userRepository.save(editor);
        userRepository.save(admin);
        userRepository.save(test);

        List<User> testUserList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            User testUser = new User("testUser" + i, BcryptUtils.hashPw("testUser" + i));
            testUser.setRole(Role.USER);
            if (i % 2 == 0) {
                testUser.setStatus(UserStatus.DEACTIVATE);
            }

            testUserList.add(testUser);
        }

        for (User testUser : testUserList) {
            userRepository.save(testUser);
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    void initWarehouses() {
        Warehouse warehouse1 = new Warehouse("Warehouse 1", "Seoul");
        Warehouse warehouse2 = new Warehouse("Warehouse 2", "Paju");

        warehouseRepository.save(warehouse1);
        warehouseRepository.save(warehouse2);
    }
}
