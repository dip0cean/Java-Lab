package com.spring.mvc.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet 을 맵핑하여 사용하기 위해서는 Archetype-wepApp 을 주입 받은 뒤, web.xml 에 직접 등록해줘야 한다.
 *
 * <servlet>
 *     <servlet-name>helloServlet</servlet-name> <!-- 해당 별칭으로 Servlet 등록 -->
 *     <servlet-class>com.spring.mvc.servlet.HelloServlet</servlet-class> <!-- 등록하고자 하는 Servlet 의 경로 설정 -->
 * </servlet>
 *
 * <servlet-mapping>
 *     <servlet-name>helloServlet</servlet-name> <!-- URL 맵핑 대상의 Servlet (등록된 Servlet 만 가능) -->
 *     <url-pattern>/hello</url-pattern> <!-- 등록된 Servlet 의 URL 맵핑 (@GetMapping, @PostMapping...) -->
 * </servlet-mapping>
 */
public class MyServlet extends HttpServlet {

    // service() 메소드를 HTTP Method 에 따라 감싼 형태의 메소드이다. 해당 메소드는 현재 GET 요청에 사용된다.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet()");
        resp.getWriter().println("<html>");
        resp.getWriter().println("<head>");
        resp.getWriter().println("<body>");
        resp.getWriter().println(String.format("Hello, %s", getServletContext().getAttribute("name")));
        resp.getWriter().println("</body>");
        resp.getWriter().println("</html");
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    // 처음 요청 시 init() 메소드를 호출하며, 이후의 요청에는 별도로 호출하지 않는다. 처음 요청에만 호출한다.
    @Override
    public void init() throws ServletException {
        System.out.println("init()");
    }
}
