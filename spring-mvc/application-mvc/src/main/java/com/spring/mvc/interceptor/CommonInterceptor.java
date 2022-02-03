package com.spring.mvc.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class CommonInterceptor implements HandlerInterceptor {

    /**
     * 핸들러를 수행하기 전에 호출되는 메소드
     * 현재 수행될 예정인 핸들러에 대한 정보를 인자로 받기 때문에 ServletFilter 보다 세밀한 로직을 구현할 수 있다.
     * Boolean 값을 반환하는데, 이는 다음 Interceptor 를 이어서 수행할지 말지를 결정하는 값이다.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param handler  현재 핸들러
     * @return Boolean : 다음 인터셉터를 수행할지 말지
     * @throws Exception 모든 예외를 호출한 메소드로 위임한다.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("CommonHandler :: preHandler");
        return true;
    }

    /**
     * 핸들러를 수행한 뒤, View 랜더링 작업을 수행하기 전에 호출된다.
     * View 에 추가적인 작업이 필요하거나 여러 핸들러에 공통적인 모델 정보를 담는데 사용 가능하다.
     * View 를 커스터마이징 할 수 있다.
     * 해당 메소드는 등록된 Interceptor 순서의 역순으로 수행된다.
     * 비동기적 요청 처리 시에는 호출되지 않는다.
     *
     * @param request      HttpServletRequest
     * @param response     HttpServletResponse
     * @param handler      현재 핸들러
     * @param modelAndView 핸들러 요청 시 반환하는 ModelAndView
     * @throws Exception 모든 예외를 호출한 메소드로 위임한다.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("CommonHandler :: postHandler");
    }

    /**
     * 핸들러 요청이 완전히 끝난 뒤에 호출된다.
     * preHandler() 에서 true 를 반환한 경우에만 호출된다.
     * 해당 메소드는 등록된 Interceptor 순서의 역순으로 수행된다.
     * 비동기적 요청 처리시에는 호출되지 않는다.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param handler  현재 핸들러
     * @param ex       핸드러 요청 처리 중 던져진 예외 객체
     * @throws Exception 모든 예외를 호출한 메소드로 위임한다.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("CommonHandler :: afterCompletion");
    }
}
