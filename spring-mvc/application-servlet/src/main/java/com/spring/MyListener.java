package com.spring;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Servlet Context Lifecycle 을 감지할 수 있는 리스너 객체
 * 이러한 리스너 객체는 여러 개의 서블릿에서 공통으로 사용하는 객체를 넣어 놓는 등으로 사용될 수 있다.
 * 리스너 객체는 Servlet Context 보다 먼저 생성되기 때문에 이후에 생성되는 Servlet Context 들의 이벤트를 감지할 수 있게 되는 것이다.
 */
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("MyListener :: Context Initialized");
        sce.getServletContext().setAttribute("name", "osean");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("MyListener :: Context Destroyed");
    }
}
