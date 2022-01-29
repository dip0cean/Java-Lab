package com.spring.mvc.config;

import com.spring.mvc.interceptor.AnotherInterceptor;
import com.spring.mvc.interceptor.CommonInterceptor;
import com.spring.mvc.interceptor.GreetingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

// Spring Boot 를 사용하는 경우, Formatter 를 Bean 으로 등록해주면 알아서 Formatter 설정을 수행한다.
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new PersonFormatter());
//    }

    /**
     * HandlerInterceptor 등록
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // CommonInterceptor
        registry.addInterceptor(new CommonInterceptor())
                .order(1);

        // GreetingInterceptor
        registry.addInterceptor(new GreetingInterceptor())
                .addPathPatterns("/interceptor/greeting")
                .order(-1);

        // AnotherInterceptor
        registry.addInterceptor(new AnotherInterceptor())
                .addPathPatterns("/interceptor/another")
                .order(-1);
    }
}
