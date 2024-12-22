package blog.devjay.logistics.web.advice;

import blog.devjay.logistics.domain.exception.UserDeactivateException;
import blog.devjay.logistics.domain.exception.UserSuspendedException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final MessageSource messageSource;

    @ExceptionHandler(UserSuspendedException.class)
    public String handleSuspendedException(UserSuspendedException e, HttpServletRequest request, Model model) {
        model.addAttribute("errorMessage", getMessageFromMessageSource(request, "user.suspended"));
        model.addAttribute("logoutBtn", true);
        model.addAttribute("status", 403);
        return "error";
    }

    @ExceptionHandler(UserDeactivateException.class)
    public String handleDeactivateException(UserDeactivateException e, HttpServletRequest request, Model model) {
        model.addAttribute("errorMessage", getMessageFromMessageSource(request, "user.deactivate"));
        model.addAttribute("logoutBtn", true);
        model.addAttribute("status", 403);
        return "error";
    }

    private String getMessageFromMessageSource(HttpServletRequest request, String code) {
        Locale locale = request.getLocale();
        return messageSource.getMessage(code, null, locale);
    }
}
