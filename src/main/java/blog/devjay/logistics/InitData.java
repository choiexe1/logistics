package blog.devjay.logistics;

import blog.devjay.logistics.domain.item.Item;
import blog.devjay.logistics.domain.user.Role;
import blog.devjay.logistics.domain.user.User;
import blog.devjay.logistics.domain.user.UserStatus;
import blog.devjay.logistics.domain.warehouse.Warehouse;
import blog.devjay.logistics.repository.item.ItemRepository;
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
    private final ItemRepository itemRepository;

    @EventListener(ApplicationReadyEvent.class)
    void init() {
        initUsers();
        initWarehouses();
        initItems();
    }

    void initUsers() {
        User user = new User("user", BcryptUtils.hashPw("user"), Role.USER);
        User editor = new User("editor", BcryptUtils.hashPw("editor"), Role.EDITOR);
        User admin = new User("admin", BcryptUtils.hashPw("admin"), Role.ADMIN);
        User test = new User("test", BcryptUtils.hashPw("test"), Role.ADMIN);

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

    void initWarehouses() {
        List<Warehouse> warehouses = new ArrayList<>();

        for (int i = 1; i <= 2; i++) {
            Warehouse warehouse = new Warehouse("Warehouse " + i,
                    i % 2 == 0 ? "Seoul" : "Paju");
            warehouses.add(warehouse);
        }

        for (Warehouse warehouse : warehouses) {
            warehouseRepository.save(warehouse);
        }
    }

    void initItems() {
        List<Item> items = new ArrayList<>();

        for (int i = 1; i <= 25; i++) {
            Item item = new Item(i % 2 == 0 ? 1L : 2L, "item" + i, i * 1000, 10);
            items.add(item);
        }

        for (Item item : items) {
            itemRepository.save(item);
        }
    }
}
