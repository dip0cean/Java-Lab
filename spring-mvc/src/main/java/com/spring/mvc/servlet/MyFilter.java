package com.spring.mvc.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * Servlet Filter 또한 web.xml 에로 별도로 등록해줘야 한다.
 *
 * <filter>
 *     <filter-name>myFilter</filter-name> <!-- 해당 별칭으로 Filter 등록 -->
 *     <filter-class>com.spring.mvc.servlet.MyFilter</filter-class> <!-- 등록하고자 하는 Filter 의 경로 -->
 * </filter>
 *
 * <filter-mapping>
 *     <filter-name>myFilter</filter-name> <!-- 맵핑하고자 하는 Filter 의 별칭 -->
 *     <servlet-name>helloServlet</servlet-name> <!-- 맵핑하고자 하는 Servlet 의 별칭 -->
 * </filter-mapping>
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter Init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter");
        // Filter 는 체인으로 연결되어 있기 때문에 다음 Filter 와 연결을 유지시켜줘야 한다.
        // 제일 마지막에 적용되는 Filter 인 경우 자동으로 Servlet 에 연결된다.
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("Filter Destroy");
    }
}
