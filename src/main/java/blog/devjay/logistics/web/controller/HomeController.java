package blog.devjay.logistics.web.controller;


import blog.devjay.logistics.domain.exception.AuthenticationException;
import blog.devjay.logistics.domain.exception.NotFoundException;
import blog.devjay.logistics.domain.user.User;
import blog.devjay.logistics.dto.user.LoginDTO;
import blog.devjay.logistics.dto.user.RegisterDTO;
import blog.devjay.logistics.service.AuthService;
import blog.devjay.logistics.service.UserService;
import blog.devjay.logistics.web.SessionManager;
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

import static blog.devjay.logistics.web.SessionConst.SESSION_ID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;
    private final AuthService authService;

    @GetMapping("/")
    public String indexView(@CurrentUser User user, Model model) {
        model.addAttribute("user", user);
        return "views/index";
    }

    @GetMapping("/login")
    public String loginView(@RequestParam(defaultValue = "/") String redirectURL,
                            @ModelAttribute("form") LoginDTO form) {
        return "views/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(defaultValue = "/") String redirectURL,
                        @Validated @ModelAttribute("form") LoginDTO form, BindingResult bindingResult,
                        HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.warn("errors = {}", bindingResult);
            return "views/login";
        }

        try {
            User user = authService.authenticate(form.getUsername(), form.getPassword());

            setSession(request, user);
            userService.updateRecentLoginAt(user.getId());

            return "redirect:" + redirectURL;
        } catch (NotFoundException e) {
            bindingResult.rejectValue("username", "user.username");
            return "views/login";
        } catch (AuthenticationException e) {
            bindingResult.rejectValue("password", "user.password");
            return "views/login";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, @CurrentUser User user) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            SessionManager.remove(user.getUsername());
            session.invalidate();
        }

        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerView(@ModelAttribute("form") RegisterDTO form) {
        return "views/register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("form") RegisterDTO form, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.warn("errors = {}", bindingResult);
            return "views/register";
        }

        User user = new User(form.getUsername(), BcryptUtils.hashPw(form.getPassword()));

        try {
            userService.create(user);
        } catch (DuplicateKeyException e) {
            bindingResult.rejectValue("username", "user.username.exist");
            log.warn("회원가입 실패: {}", e.getMessage());
            return "views/register";
        }

        redirectAttributes.addFlashAttribute("status", true);
        return "redirect:/login";
    }

    private void setSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_ID, user);

        SessionManager.set(user.getUsername(), session);
    }
}
