package com.hpl.nownew;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class TokenConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
        // 这个方法才能在拦截器中自动注入查询数据库的对象
    }

    // 将路径改为demo/**的话，那么demo/下的所有访问都会被拦截，必须先登入
    @Override
    public void addInterceptors(InterceptorRegistry registry ){
        registry.addInterceptor(tokenInterceptor()).addPathPatterns("/demo1/**");
        //配置生成器：添加一个拦截器，拦截路径为API以后的路径
    }
}

