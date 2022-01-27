package com.spring.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

// Spring Boot 를 사용하는 경우, Fomatter 를 Bean 으로 등록해주면 알아서 Formatter 설정을 수행한다.
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new PersonFormatter());
//    }
}
