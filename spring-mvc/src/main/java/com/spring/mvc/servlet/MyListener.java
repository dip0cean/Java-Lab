package com.spring.mvc.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Servlet Listener 또한 web.xml 에서 별도로 등록해줘야 한다.
 *
 * <listener>
 *     <listener-class>com.spring.mvc.servlet.MyListener</listener-class>
 * </listener>
 */
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context Initialized");
        sce.getServletContext().setAttribute("name", "Ocean!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context Destroyed");
    }
}
