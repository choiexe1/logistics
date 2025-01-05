package blog.devjay.logistics.service;

import blog.devjay.logistics.domain.exception.AuthenticationException;
import blog.devjay.logistics.domain.user.User;
import blog.devjay.logistics.web.utils.BcryptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;

    public User authenticate(String username, String password) {
        User user = userService.findByUsername(username);

        if (!BcryptUtils.checkPw(password, user.getPassword())) {
            throw new AuthenticationException("패스워드가 일치하지 않습니다.");
        }

        return user;
    }
}
