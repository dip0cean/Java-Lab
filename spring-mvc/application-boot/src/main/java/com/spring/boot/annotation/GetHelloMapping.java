package com.spring.boot.annotation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

@Documented // 해당 어노테이션을 사용한 코드의 문서에 어노테이션 정보를 표기할지를 결정한다.
@Target(ElementType.METHOD) // 어노테이션이 적용가능한 스코프 영역을 설정한다.
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 정보의 라이프사이클 시점을 설정한다.
@RequestMapping(value = "/hello", method = RequestMethod.GET)
public @interface GetHelloMapping {
}
