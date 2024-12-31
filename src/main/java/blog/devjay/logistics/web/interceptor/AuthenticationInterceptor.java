package blog.devjay.logistics.web.interceptor;

import static blog.devjay.logistics.web.SessionConst.SESSION_ID;

import blog.devjay.logistics.domain.user.User;
import blog.devjay.logistics.web.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestUri = request.getRequestURI();
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SESSION_ID) == null) {
            response.sendRedirect("/login?redirectURL=" + requestUri);
            return false;
        }

        User user = getUser(session);
        String username = user.getUsername();

        isDuplicateLogin(username, session);
        return true;
    }

    private User getUser(HttpSession session) {
        return (User) session.getAttribute(SESSION_ID);
    }

    private void isDuplicateLogin(String username, HttpSession session) {
        HttpSession oldSession = SessionManager.get(username);

        if (oldSession != session) {
            oldSession.invalidate();
        }
    }
}