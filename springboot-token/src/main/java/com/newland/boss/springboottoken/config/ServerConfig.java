package com.newland.boss.springboottoken.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lfp
 * @Title: ServerConfig
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/12/15 16:45
 */
@Configuration
public class ServerConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**");
    }
    @Bean
    public ServiceFilter authenticationInterceptor() {
        return new ServiceFilter();
    }
}
