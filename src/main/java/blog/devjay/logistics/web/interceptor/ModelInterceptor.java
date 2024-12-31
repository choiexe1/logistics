package blog.devjay.logistics.web.interceptor;

import blog.devjay.logistics.domain.user.Role;
import blog.devjay.logistics.domain.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ModelInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        User user = (User) request.getAttribute("user");

        if (modelAndView != null) {
            setModelAndView(user, request);
        }
    }

    private void setModelAndView(User user, HttpServletRequest request) {
        request.setAttribute("currentUser", user);
        request.setAttribute("isUser", user.getRole() == Role.USER);
        request.setAttribute("isEditor", user.getRole() == Role.EDITOR);
        request.setAttribute("isAdmin", user.getRole() == Role.ADMIN);
        request.setAttribute("uri", request.getRequestURI());
    }
}
