package com.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// @ComponentScan 을 통해 모든 Bean 객체를 Spring IoC Container 에 등록한다.
// 별도의 설정이 없다면 @Configuration 하위 패키지에 존재하는 컴포넌트 Bean 객체를 찾아 등록한다.
@ComponentScan
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

/*
    WebMvcConfigurer 인터페이스를 구현한다면 별도로 ViewResolver 를 구현하지 않아도 된다.
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }
 */

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/", ".jsp");
    }
}
