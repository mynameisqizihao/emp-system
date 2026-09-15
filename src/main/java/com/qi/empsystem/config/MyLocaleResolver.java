package com.qi.empsystem.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

//实现区域解析器LocaleResolver接口，让SpringMVC处理国际化（语言逻辑）的时候用我们的自定义区域解析器
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String language = request.getParameter("l");
        System.out.println("当前从URL里读取到的语言参数为：" + language);
        Locale locale = Locale.getDefault();
        if (!StringUtils.isEmpty(language)) {
            String[] split = language.split("_");
            locale = new Locale(split[0],split[1]);
        }
        //SpringMVC拿到现在的Locale，就知道该去那个Properties找文件了
        return locale;
    }

    //设置语言，用不上
    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
