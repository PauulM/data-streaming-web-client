package datastreaming.webclient.configuration;

import datastreaming.webclient.interceptor.LoggerInterceptor;
import datastreaming.webclient.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor()).excludePathPatterns("/js/**","/css/**");
        registry.addInterceptor(new LoginInterceptor()).excludePathPatterns("/","/login/**","/js/**","/css/**");
    }
}
