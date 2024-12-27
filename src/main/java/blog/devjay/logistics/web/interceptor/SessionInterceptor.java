package blog.devjay.logistics.web.interceptor;

import static blog.devjay.logistics.web.SessionConst.SESSION_ID;

import blog.devjay.logistics.domain.exception.UserDeactivateException;
import blog.devjay.logistics.domain.exception.UserSuspendedException;
import blog.devjay.logistics.domain.user.Role;
import blog.devjay.logistics.domain.user.User;
import blog.devjay.logistics.domain.user.UserStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@RequiredArgsConstructor
@Slf4j
public class SessionInterceptor implements HandlerInterceptor {
    private final static String[] ADMIN_AUTH_LIST = {"/admin"};
    private final static String[] EDITOR_AUTH_LIST = {"/warehouse"};


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(SESSION_ID);
        UserStatus status = user.getStatus();

        if (!isActivateUser(status)) {
            return false;
        }

        request.setAttribute("user", user);
        String requestURI = request.getRequestURI();

        boolean isEditorPath = PatternMatchUtils.simpleMatch(EDITOR_AUTH_LIST, requestURI);
        boolean isAdminPath = PatternMatchUtils.simpleMatch(ADMIN_AUTH_LIST, requestURI);
        Role role = user.getRole();

        if (isAdminPath && role != Role.ADMIN) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return false;
        }

        if (isEditorPath && role == Role.USER) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return false;
        }

        return true;
    }

    private boolean isActivateUser(UserStatus status) {

        if (status == UserStatus.SUSPENDED) {
            throw new UserSuspendedException();
        }

        if (status == UserStatus.DEACTIVATE) {
            throw new UserDeactivateException();
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        User user = (User) request.getAttribute("user");

        if (modelAndView != null) {
            setRequestAttribute(user, request);
        }
    }

    private void setRequestAttribute(User user, HttpServletRequest request) {
        request.setAttribute("currentUser", user);
        request.setAttribute("isEditor", user.getRole() == Role.EDITOR);
        request.setAttribute("isAdmin", user.getRole() == Role.ADMIN);
        request.setAttribute("uri", request.getRequestURI());
    }
}
