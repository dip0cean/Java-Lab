package com.spring;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filter 는 Chain 구조를 가지며, doFilter() 메소드에서 Chaining 해주지 않으면 다음 Filter 로 진행되지 않는다.
 * 맵핑된 URL 이나 Servlet 을 호출하지 않더라도 애플리케이션 구동 시점에서 Filter 는 초기화된다.
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter :: init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter :: doFilter()");
        // Chaining
        // 맨 마지막 Filter 의 chaining 과정을 거치면 최종적으로 Servlet 으로 도달하게 된다.
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter :: destroy()");
    }
}
