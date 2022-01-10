package com.spring.basic.chapter_05_Environment.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProfileAppRunner implements ApplicationRunner {

    /**
     * ApplicationContext 는 여러 클래스를 상속 받고 있는데, EnvironmentCapable 클래스의 Profile 을 알아보자.
     * 각각의 상황에 따라 다른 Bean 을 사용해야 하는 경우, 특정한 경우에 특별한 Bean 을 사용해야 하는 경우 > Environment 를 사용한다.
     */
    private final ApplicationContext ctx;
    // Profile = test 일 때 주입 받는다.
//    private final ProfileRepository profileRepository;
    // Profile != test 일 때 주입 받는다.
//    private final BeanRepository beanRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Environment environment = ctx.getEnvironment();
        log.info("Active Environments :: {}", Arrays.toString(environment.getActiveProfiles()));
        log.info("Default Environments :: {}", Arrays.toString(environment.getDefaultProfiles()));
        log.info("Property app.name :: {}", environment.getProperty("app.name"));
    }
}
