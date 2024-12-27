package blog.devjay.logistics;

import blog.devjay.logistics.repository.item.ItemRepository;
import blog.devjay.logistics.repository.user.UserRepository;
import blog.devjay.logistics.repository.warehouse.WarehouseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class LogisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogisticsApplication.class, args);
    }

    @Bean
    @Profile("local")
    public InitData initData(UserRepository userRepository, WarehouseRepository warehouseRepository,
                             ItemRepository itemRepository) {
        return new InitData(userRepository, warehouseRepository, itemRepository);
    }
}
