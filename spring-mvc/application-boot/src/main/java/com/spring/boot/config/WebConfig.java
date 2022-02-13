package com.spring.boot.config;

import com.spring.boot.interceptor.VisitInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * MatrixVariable
     *
     * @param configurer PathMatchConfigurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper helper = new UrlPathHelper();
        helper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(helper);
    }

    /**
     * VisitInterceptor
     *
     * @param registry InterceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VisitInterceptor());
    }
}