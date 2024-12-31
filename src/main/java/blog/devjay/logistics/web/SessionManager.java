package blog.devjay.logistics.web;

import static blog.devjay.logistics.web.SessionConst.SESSION_ID;

import jakarta.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    private static final Map<String, HttpSession> sessionMap = new ConcurrentHashMap<>();

    public static void set(String username, HttpSession session) {
        if (sessionMap.containsKey(username)) {
            HttpSession oldSession = sessionMap.get(username);

            if (oldSession != null && isValid(oldSession)) {
                oldSession.invalidate();
            }
        }

        sessionMap.put(username, session);
    }

    public static HttpSession get(String username) {
        return sessionMap.get(username);
    }

    public static void remove(String username) {
        sessionMap.remove(username);
    }

    public static boolean isValid(HttpSession session) {
        try {
            session.getAttribute(SESSION_ID);
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }
}