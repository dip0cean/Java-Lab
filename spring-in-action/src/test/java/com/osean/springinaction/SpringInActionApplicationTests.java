package com.osean.springinaction;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Spring Application Context 가 성공적으로 로드될 수 있는지 확인하기 위한 테스트 클래스
 */
@SpringBootTest // 테스트 시작을 JUnit 에게 알려주는 어노테이션, SpringApplication.run() 메소드와 비슷하다.
class SpringInActionApplicationTests {

	@Test
	void contextLoads() {
	}

}
