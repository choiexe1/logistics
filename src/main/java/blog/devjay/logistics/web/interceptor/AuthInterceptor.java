package blog.devjay.logistics.web.interceptor;

import static blog.devjay.logistics.web.SessionConst.SESSION_ID;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestUri = request.getRequestURI();
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SESSION_ID) == null) {
            response.sendRedirect("/login?redirectURL=" + requestUri);
            return false;
        }

        return true;
    }
}
