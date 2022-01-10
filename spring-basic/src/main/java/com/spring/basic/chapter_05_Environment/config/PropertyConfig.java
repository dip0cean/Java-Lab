package com.spring.basic.chapter_05_Environment.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@PropertySource("classpath:/app.yml")
public class PropertyConfig {

    // application.yml
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${app.name}")
    private String appName;

    // app.yml
    @Value("${say}")
    private String say;

    @PostConstruct
    public void postConstruct() {
        log.info("application.yml > spring.datasource.username :: {}", username);
        log.info("application.yml > app.name :: {}", appName);
        log.info("app.yml > say :: {}", say);
    }
}
