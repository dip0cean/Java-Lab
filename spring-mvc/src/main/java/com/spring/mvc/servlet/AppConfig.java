package com.spring.mvc.servlet;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <context-param>
 *     <param-name>contextClass</param-name>
 *     <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
 * </context-param>
 *
 * <context-param>
 *     <param-name>contextConfigLocation</param-name>
 *     <param-value>com.spring.mvc.servlet.AppConfig</param-value>
 * </context-param>
 *
 * <listener>
 *     <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
 * </listener>
 *
 * ContextLoaderListener 가 Application Context(AnnotationConfigApplicationContext) 를 만들 때 context-param 으로 등록된 AppConfig 를 확인하고
 * AppConfig 에 적용된 @ComponentScan 를 이용해 HelloService 를 Bean 으로 등록한다.
 */
@Configuration
@ComponentScan
public class AppConfig {
}
