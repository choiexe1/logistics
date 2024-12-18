package blog.devjay.logistics;

import blog.devjay.logistics.repository.user.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LogisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogisticsApplication.class, args);
    }

    @Bean
    public InitData initData(UserRepository userRepository) {
        return new InitData(userRepository);
    }
}
