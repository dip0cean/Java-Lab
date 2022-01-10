package com.spring.basic.chapter_08_Resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResourceAppRunner implements ApplicationRunner {

    // AnnotationConfigServletWebServerApplicationContext
    private final ApplicationContext resourceLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*
        ClassPathXmlApplicationContext 와 FileSystemXmlApplicationContext 는 내부적으로 서로 다른 구현체를 사용한다.
        항상 존재하는 리소스라는 가정을 하지 않는다.
        classpath 경로 기준으로 location 파일을 조회 (location 은 내부적으로 Resource 객체로 추상화된다.)
            ApplicationContext classpathByCtx = new ClassPathXmlApplicationContext("xxx.xml");
        파일 시스템 경로 기준으로 location 파일을 조회 (location 은 내부적으로 Resource 객체로 추상화된다.)
            ApplicationContext fileSystemByCtx = new FileSystemXmlApplicationContext("yyy.xml");
         */

        /*
        AnnotationConfigServletWebServerApplicationContext 으로 ApplicationContext 의 타입이 지정되는 경우 (spring-boot-starter-web 의존성 추가)
        ResourceLoader 타입은 자동으로 ServletContextResource 로 설정되며,
        위의 주석처럼 별도로 ApplicationContext 타입을 지정하거나 resourceLoader.getResource() 메소드의 location 인자로 "classpath:/" 혹은 "file:/" 를 접두어로 추가하면
        해당 ApplicationContext 타입에 맞는 ResourceLoader 로 설정된다.
         */
        Resource resource =
                resourceLoader.getResource("messages.properties"); // ServletContextResource ex) WEB-INF/messages.properties
//              resourceLoader.getResource("classpath:/messages.properties"); // ClassPathResource
//              resourceLoader.getResource("file:/messages.properties"); // FileUrlResource
//              resourceLoader.getResource("http://www.naver.com"); // UrlResource

        System.out.println();
        log.info("ResourceLoader.getClass() :: {}", resourceLoader.getClass());
        log.info("Resource.getClass() :: {}", resource.getClass());
        log.info("Resource Description :: {}", resource.getDescription());
        log.info("hasResource? :: {}", resource.exists());
        /*
        내장 Tomcat 서버는 별도로 classpath 를 지정하지 않기 때문에 ResourceLoader 가 ServletContextResource 일 경우,
        지정된 경로를 기준으로 파일을 찾을 수 없기 때문에 에러를 발생하며 애플리케이션을 종료시킨다.

        log.info("File.readString() :: {}", Files.readString(Path.of(resource.getURI())));
         */
    }
}
