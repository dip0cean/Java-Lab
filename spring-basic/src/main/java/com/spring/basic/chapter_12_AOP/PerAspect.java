package com.spring.basic.chapter_12_AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PerAspect {

    @Around("execution(* com.spring.basic.chapter_12_AOP.EventService.*(..))") // 메소드를 감싸는 형태로 적용된다. (메소드의 Proxy)
    public Object loObjectByExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object retVal = proceedingJoinPoint.proceed();
        log.info("@Around(\"execution(* com.spring.basic.chapter_12_AOP.EventService.*(..))\") > 메소드 수행 시 걸린 시간 :: {}ms", (System.currentTimeMillis() - begin));

        return retVal;
    }

    @Around("@annotation(PerAnnotation)")
    public Object loObjectByAnnotation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object retVal = proceedingJoinPoint.proceed();
        log.info("@Around(\"@annotation(PerAnnotation)\") > 메소드 수행 시 걸린 시간 :: {}ms", (System.currentTimeMillis() - begin));

        return retVal;
    }
}
