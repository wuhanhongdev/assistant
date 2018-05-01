package com.power.assistant.config;

import com.power.assistant.core.advice.LoginAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author wuhanhong
 * @date 2018 - 04 - 30
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Bean
    public LoginAdvice securityInterceptor() {
        return new LoginAdvice();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor()).excludePathPatterns("/static/*").excludePathPatterns("/error").addPathPatterns("/**");
    }

}