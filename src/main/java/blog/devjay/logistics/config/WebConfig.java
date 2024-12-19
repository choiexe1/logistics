package blog.devjay.logistics.config;

import blog.devjay.logistics.web.argumentresolver.CurrentUserArgumentResolver;
import blog.devjay.logistics.web.interceptor.AuthInterceptor;
import blog.devjay.logistics.web.interceptor.SessionInterceptor;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final static List<String> AUTH_WHITELIST =
            List.of("/login", "/logout", "/register", "/css/*", "/error", "*.ico");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .excludePathPatterns(AUTH_WHITELIST)
                .order(1);
        registry.addInterceptor(new SessionInterceptor())
                .excludePathPatterns(AUTH_WHITELIST)
                .order(2);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CurrentUserArgumentResolver());
    }
}
