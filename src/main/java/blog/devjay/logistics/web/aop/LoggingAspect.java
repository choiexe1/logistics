package blog.devjay.logistics.web.aop;

import blog.devjay.logistics.domain.log.Log;
import blog.devjay.logistics.domain.log.ResponseStatus;
import blog.devjay.logistics.domain.user.User;
import blog.devjay.logistics.service.LogService;
import blog.devjay.logistics.web.aop.annotation.Logging;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;

import static blog.devjay.logistics.web.SessionConst.SESSION_ID;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {
    private final LogService logService;

    @Around("@annotation(logging)")
    public Object logging(ProceedingJoinPoint joinPoint, Logging logging) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String username = getCurrentUsername(request);

        Object[] args = joinPoint.getArgs();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String params = getParamsAsString(request);
        BindingResult bindingResult = null;

        Object result = joinPoint.proceed();

        for (Object arg : args) {
            if (arg instanceof BindingResult bindingResultArg) {
                bindingResult = bindingResultArg;
            }
        }

        if (bindingResult != null && bindingResult.hasErrors()) {
            logService.create(
                    new Log(username, uri, method, params, ResponseStatus.FAIL));
        } else {
            logService.create(
                    new Log(username, uri, method, params, ResponseStatus.SUCCESS));
        }

        return result;
    }

    private String getParamsAsString(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();

            boolean notContains =
                    !"password".equals(key) && !"username".equals(key) && !"redirectURL".equals(key) && !"size".equals(
                            key);
            if (notContains
                    && values != null && values.length > 0
                    && !values[0].isEmpty()) {
                String valueString = String.join(",", values);

                sb.append(key).append("=").append(valueString).append(", ");
            }
        }

        if (!sb.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    private String getCurrentUsername(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "Anonymous";
        }

        User user = (User) session.getAttribute(SESSION_ID);
        return user.getUsername();
    }
}
