package blog.devjay.logistics;

import blog.devjay.logistics.domain.user.Role;
import blog.devjay.logistics.domain.user.User;
import blog.devjay.logistics.repository.user.UserRepository;
import blog.devjay.logistics.web.utils.BcryptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class InitData {
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    void initUsers() {
        User user = new User("user", BcryptUtils.hashPw("user"));
        User editor = new User("editor", BcryptUtils.hashPw("editor"));
        User admin = new User("admin", BcryptUtils.hashPw("admin"));

        editor.setRole(Role.EDITOR);
        admin.setRole(Role.ADMIN);

        userRepository.save(user);
        userRepository.save(editor);
        userRepository.save(admin);
    }
}
