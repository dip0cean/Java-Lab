package com.spring.basic.chapter_03_ComponentScan.service;

import com.spring.basic.chapter_03_ComponentScan.repository.IncludeTestRepository;
import com.spring.basic.chapter_03_ComponentScan.repository.ExcludeTestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComponentScanTestService {

    private final IncludeTestRepository includeTestRepository;
//    private final ExcludeTestRepository excludeTestRepository;

    @PostConstruct
    public void postConstruct() {
        log.info("@IncludeTest :: IncludeTestRepository 인스턴스를 주입 받은 뒤 실행되는 메소드입니다. {}", includeTestRepository.toString());
//        log.info("@ExcludeTest :: ExcludeTestRepository 인스턴스를 주입 받은 뒤 실행되는 메소드입니다. {}", excludeTestRepository.toString());
    }
}
