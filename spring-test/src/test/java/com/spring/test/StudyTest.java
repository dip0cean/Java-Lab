package com.spring.test;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// DisplayNameGenerator.ReplaceUnderscores > 언더바를 공백으로 바꿔준다.
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    /**
     * 전체 테스트를 진행할 때, 모든 테스트 메소드 실행 전에 실행되는 메소드이다.
     * 모든 우선순위 중 가장 먼저 수행되며, static 제어자는 필수 요소이다.
     */
    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    /**
     * 단일 테스트 메소드 혹은 전체 테스트 메소드의 테스트를 진행하기 전에 실행되는 메소드이다.
     * static 제어자는 필수 요소가 아니며, @BeforeAll 다음에 수행된다.
     */
    @BeforeEach
    void beforeEach() {
        System.out.println("@BeforeEach");
    }

    /**
     * 단위 테스트를 진행하기 위해서 테스트 메소드에 @Test 을 선언해줘야 한다.
     */
    @Test
    void create_1() {
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create_1");
    }

    /**
     * 전체 테스트 중 특정 테스트 메소드를 제외하고 싶다면 @Disabled 를 선언한다.
     */
    @Test
    @Disabled
    void create_2() {
        System.out.println("create_2");
    }

    @Test
    @DisplayName("@DisplayName 이용해서 메소드명 바꾸기")
    void create_3() {System.out.println("create_3");}

    /**
     * 단일 테스트 메소드 혹은 전체 테스트 메소드의 테스트를 진행한 뒤에 실행되는 메소드이다.
     * static 제어자는 필수 요소가 아니며, @Test 다음에 수행된다.
     */
    @AfterEach
    void afterEach() {
        System.out.println("@AfterEach");
    }

    /**
     * 전체 테스트를 진행할 때, 모든 테스트 메소드 실행 후에 실행되는 메소드이다.
     * 모든 우선순위 중 가장 마지막에 수행되며, static 제어자는 필수 요소이다.
     */
    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll");
    }
}