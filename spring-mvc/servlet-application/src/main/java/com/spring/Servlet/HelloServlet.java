package com.spring.servlet;

import com.spring.servlce.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("HelloServlet :: init()");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet :: doGet()");

        // ContextLoaderListener 에 의해 ServletContext 라는 공통의 Context 에 생성된 RootWebApplicationContext 를 등록한다.
        // (* RootWebApplicationContext 는 AppConfig 에 의해서 생성된다.)
        ApplicationContext applicationContext = (ApplicationContext) req.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

        // ServletContext 에 등록된 ApplicationContext 를 통해 주입된 Bean 객체를 가져올 수 있다.
        HelloService helloService = applicationContext.getBean(HelloService.class);

        resp.getWriter().println("<html>");
        resp.getWriter().println("<head>");
        resp.getWriter().println("<body>");
        resp.getWriter().println(String.format("<h1>MyListener :: %s</h1>", getServletContext().getAttribute("name")));
        resp.getWriter().println(String.format("<h1>HelloService.sayHello() :: %s!</h1>", helloService.sayHello()));
        resp.getWriter().println("</body>");
        resp.getWriter().println("</head>");
        resp.getWriter().println("</html>");
    }

    @Override
    public void destroy() {
        System.out.println("HelloServlet :: destroy()");
    }
}
