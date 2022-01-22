package com.spring;

/**
 * ContextLoaderContext 가 Application Context(= AnnotationConfigApplicationContext) 를 만들 때
 * context-param 으로 등록된 AppConfig 를 확인하고, AppConfig 에 적용된 @ComponentScan 어노테이션을 이용해 HelloService 를 Bean 으로 등록한다.
 */
//@Configuration
//@ComponentScan(excludeFilters = @ComponentScan.Filter(Controller.class))
public class AppConfig {
}
