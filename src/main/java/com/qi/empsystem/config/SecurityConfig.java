package com.qi.empsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthResponseHandler authResponseHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/login.html", "/register.html","/user/login","/user/register",
                        "/user/checkName","/user/checkPassword",
                        "/css/**","/js/**","/img/**","/favicon.ico",
                        "/swagger-ui/**","swagger-ui.html","/v3/api-docs/**",
                        "doc.html"
                ).permitAll()
                .anyRequest().authenticated()
        ).formLogin(form -> form
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                // 登录表单的参数名。默认是 username，这里统一改成 name，
                // 前端 SPA 和 Thymeleaf 的 login.html 都提交 name
                .usernameParameter("name")
                .defaultSuccessUrl("/index.html",true)
                // 登录失败：SPA 拿到 401 + JSON，页面请求跳 /login.html?error
                .failureHandler(authResponseHandler)
                .permitAll()
        ).exceptionHandling(ex -> ex
                // 未登录时：SPA 拿到 401 + JSON，页面请求跳 /login.html
                .authenticationEntryPoint(authResponseHandler)
        ).csrf(csrf -> csrf.disable()
        ).logout(logout -> logout
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/login.html")
                .permitAll()
        ).rememberMe(remember -> remember
                .key("empSystemSecretKey")
                .tokenValiditySeconds(14 * 24 * 60 * 60)
                .rememberMeParameter("remember-me")
                .userDetailsService(userDetailsService)
        );
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
