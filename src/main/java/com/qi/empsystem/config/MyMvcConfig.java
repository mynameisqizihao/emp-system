package com.qi.empsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC 的少量自定义配置。
 *
 * 注意：登录拦截已交给 Spring Security（见 SecurityConfig），
 * 原来这里基于 LoginHandlerInterceptor 的拦截器注册代码已经废弃并删除。
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    /**
     * 国际化解析器：根据请求参数 l 决定使用哪种语言
     * 例如 /login.html?l=zh_CN
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}
