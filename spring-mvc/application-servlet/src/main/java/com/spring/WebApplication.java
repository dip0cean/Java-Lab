package com.spring;

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
        // @EnableWebMvc 어노테이션을 사용할 경우 필수 설정값이다.
        // ServletContext 를 참조하고 있기 때문에 주입해줘야 한다.
        context.setServletContext(servletContext);
        // WebConfig 클래스를 기준으로 Bean 객체를 설정한다.
        context.register(WebConfig.class);
        context.refresh();

        // DispatcherServlet 생성
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic app = servletContext.addServlet("app", dispatcherServlet);
        app.addMapping("/app/*"); // /app 의 하위 경로 모두를 포함한다.

        /*
            AnnotationConfigWebApplicationContext 에서 왜 어노테이션으로 등록한 Bean 객체를 Spring IoC 컨테이너에 등록하지 못하는지 문제 해결하기
            - WebConfig > 자신이 속한 패키지 내부에 존재하는 컴포넌트 Bean 객체만 Spring IoC Container 에 등록한다.
            - 원인 : WebConfig 클래스 파일을 config 디렉토리로 옮겨놔서 AnnotationConfigApplicationContext 가 config 하위에 존재하는 컴포넌트 Bean 객체를 찾을 수가 없어 등록하지 못함
            - 해결 : WebConfig 클래스를 상위 디렉토리로 옮겨서 해결
         */
    }
}
