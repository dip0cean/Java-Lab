package com.spring.basic.chapter_02_Autowired.service;

import com.spring.basic.chapter_02_Autowired.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println();
        log.info("@Bean :: FoodRepository 인스턴스를 주입 받은 뒤 실행되는 메소드입니다. {}", foodRepository.toString());
    }
}
