package com.spring.basic.chapter_05_Environment.config;

import com.spring.basic.chapter_05_Environment.repository.ProfileRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class ProfileConfig {

    @Bean
    public ProfileRepository profileRepository() {
        return new ProfileRepository();
    }
}
