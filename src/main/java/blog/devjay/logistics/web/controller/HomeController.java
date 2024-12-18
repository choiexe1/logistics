package blog.devjay.logistics.web.controller;


import static blog.devjay.logistics.web.SessionConst.SESSION_ID;

import blog.devjay.logistics.domain.exception.UserNotFoundException;
import blog.devjay.logistics.domain.user.User;
import blog.devjay.logistics.dto.LoginDTO;
import blog.devjay.logistics.dto.RegisterDTO;
import blog.devjay.logistics.service.UserService;
import blog.devjay.logistics.web.argumentresolver.CurrentUser;
import blog.devjay.logistics.web.utils.BcryptUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

    @GetMapping("/")
    public String homeView(@CurrentUser User user, Model model) {
        model.addAttribute("user", user);
        return "home";
    }

    @GetMapping("/login")
    public String loginView(@ModelAttribute("form") LoginDTO form) {
        return "login";
    }


    @PostMapping("/login")
    public String login(@RequestParam(defaultValue = "/login") String redirectURL,
                        @Validated @ModelAttribute("form") LoginDTO form, BindingResult bindingResult,
                        HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.warn("errors = {}", bindingResult);
            return "/login";
        }

        try {
            // 사용자 인증 처리
            User user = userService.findByUsername(form.getUsername());
            boolean isMatched = BcryptUtils.checkPw(form.getPassword(), user.getPassword());

            if (!isMatched) {
                bindingResult.rejectValue("password", "user.password");
                return "/login";
            }

            // 기존 세션이 있으면 무효화하고 새 세션 생성
            HttpSession existingSession = request.getSession(false);  // 기존 세션 가져오기
            if (existingSession != null) {
                existingSession.invalidate();  // 기존 세션 무효화
            }

            // 새로운 세션 생성
            HttpSession newSession = request.getSession(true);  // 새로운 세션 생성
            newSession.setAttribute(SESSION_ID, user);  // 세션에 사용자 정보 저장

            return "redirect:/";  // 로그인 후 리다이렉트

        } catch (UserNotFoundException e) {
            bindingResult.rejectValue("username", "user.username");
            return "/login";
        }
    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request, @CurrentUser User user) {
        HttpSession session = request.getSession(false);
        session.invalidate();

        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerView(@ModelAttribute("form") RegisterDTO form) {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("form") RegisterDTO form, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.warn("errors = {}", bindingResult);
            return "/register";
        }

        User user = new User(form.getUsername(), BcryptUtils.hashPw(form.getPassword()));

        try {
            userService.register(user);
        } catch (DuplicateKeyException e) {
            bindingResult.rejectValue("username", "user.username.exist");
            log.warn("회원가입 실패: {}", e.getMessage());
            return "/register";
        }

        redirectAttributes.addFlashAttribute("status", true);
        return "redirect:/login";
    }
}
