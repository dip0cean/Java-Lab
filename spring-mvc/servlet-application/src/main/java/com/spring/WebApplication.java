package com.spring;

import com.spring.config.WebConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * web.xml 없이 DispatcherServlet 생성하고 Spring IoC Container 에 등록하기
 */
public class WebApplication implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // ApplicationContext 생성
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);
        context.refresh();

        // DispatcherServlet 생성
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic app = servletContext.addServlet("app", dispatcherServlet);
        app.addMapping("/app/*"); // /app 의 하위 경로 모두를 포함한다.

        // AnnotationConfigWebApplicationContext 에서 왜 어노테이션으로 등록한 Bean 객체를 Spring IoC 컨테이너에 등록하지 못하는지 문제 해결하기
    }
}
