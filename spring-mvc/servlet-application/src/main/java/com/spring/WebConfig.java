package com.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan // @ComponentScan 을 통해 모든 Bean 객체를 Spring IoC Container 에 등록한다.
public class WebConfig {
}
