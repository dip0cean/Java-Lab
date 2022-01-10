package com.spring.basic.chapter_02_Autowired.config;

import com.spring.basic.chapter_02_Autowired.repository.FoodRepository;
import com.spring.basic.chapter_02_Autowired.service.FoodService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FoodConfig {

    @Bean
    public FoodRepository foodRepository() {
        return new FoodRepository();
    }

    @Bean
    public FoodService foodService() {
        return new FoodService(foodRepository());
    }
}
