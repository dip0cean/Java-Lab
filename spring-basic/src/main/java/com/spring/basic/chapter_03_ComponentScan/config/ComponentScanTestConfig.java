package com.spring.basic.chapter_03_ComponentScan.config;

import com.spring.basic.chapter_03_ComponentScan.annotaion.ExcludeTest;
import com.spring.basic.chapter_03_ComponentScan.annotaion.IncludeTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@ComponentScan(
        basePackages = "com.spring.basic.chapter_03_ComponentScan.repository",
        // 해당 Filter 어노테이션에 속한 Bean 객체 제외
//        excludeFilters = {
//                @ComponentScan.Filter(
//                        type = FilterType.ANNOTATION,
//                        classes = ExcludeTest.class),
//        },
        // 해당 Filter 어노테이션에 속한 Bean 객체는 IoC Container 에 등록
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = IncludeTest.class
                )
        }
)
@Configuration
public class ComponentScanTestConfig {
}
