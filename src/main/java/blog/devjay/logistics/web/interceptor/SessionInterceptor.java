package blog.devjay.logistics.web.interceptor;

import static blog.devjay.logistics.web.SessionConst.SESSION_ID;

import blog.devjay.logistics.domain.user.Role;
import blog.devjay.logistics.domain.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    private final static String[] ADMIN_AUTH_LIST = {"/admin"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(SESSION_ID);

        request.setAttribute("user", user);
        String requestURI = request.getRequestURI();

        boolean isAdminPath = PatternMatchUtils.simpleMatch(ADMIN_AUTH_LIST, requestURI);

        if (isAdminPath && user.getRole() != Role.ADMIN) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        User user = (User) request.getAttribute("user");

        if (modelAndView != null) {
            setModelAndView(user, request, modelAndView);
        }
    }

    private void setModelAndView(User user, HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.addObject("user", user);
        modelAndView.addObject("isEditor", user.getRole() == Role.EDITOR);
        modelAndView.addObject("isAdmin", user.getRole() == Role.ADMIN);
        modelAndView.addObject("uri", request.getRequestURI());
    }
}
