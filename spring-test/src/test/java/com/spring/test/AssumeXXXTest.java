package com.spring.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AssumeXXXTest {

    @Test
    @DisplayName("assumeTrue() 를 통해 테스트 조건 설정하기")
    public void assumeTest_1() {
        // $ vi ~./zshrc
        // export TEST_ENV=LOCAL
        String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);

        // assumeTrue() 를 통과해야 다음 테스트 코드를 수행한다.
        assumeTrue("LOCAL".equalsIgnoreCase(test_env));

        NullPointerException npe = assertThrows(NullPointerException.class, () -> new Study(-10, ""));
        assertEquals("스터디명을 입력해주세요.", npe.getMessage());
    }

    @Test
    @DisplayName("assumingThat() 을 이용해 테스트 조건에 부합하면 특정 로직 수행하기")
    public void assumeTest_2() {
        String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);

        // assumingThat() 의 통과 조건에 부합해야 지정된 테스트 코드를 수행한다.
        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            Study study = new Study(-10, "TEST STUDY");
            assertAll(() -> assertTrue(study.getLimit() >= 0, "study 최대 참여 인원은 1명 이상이여야 합니다."));
        });
    }

    @Test
    @DisplayName("@EnabledOnOs() 어노테이션을 통해 특정 OS 에서만 테스트 수행하기")
    @EnabledOnOs({OS.MAC, OS.LINUX}) // Mac OS 와 Linux OS 에서만 해당 테스트를 수행한다.
    public void assumeTest_3() {
        Study study = new Study(10, "Hello Study");
        assertAll(() -> assertTrue(study.getLimit() >= 0, "study 최대 참여 인원은 1명 이상이여야 합니다."),
                () -> assertThrows(NullPointerException.class, () -> new Study(-10, "No Exception Test")));
    }

    @Test
    @DisplayName("@DisabledOnOs() 어노테이션을 통해 특정 OS 에서 테스트를 수행할 수 없도록 설정하기")
    @DisabledOnOs(OS.MAC) // Mac OS 에서는 해당 테스트를 수행할 수 없다.
    public void assumeTest_4() {
        String test_env = System.getenv("TEST_ENV");
        assumingThat("TEST".equalsIgnoreCase(test_env), () -> System.out.println("Test Success"));
    }

    @Test
    @DisplayName("특정 JRE 버전에서만 해당 테스를 수행한다.")
    @EnabledOnJre(JRE.JAVA_11) // Java 11 버전에서만 해당 테스트를 수행한다.
    @DisabledOnJre(JRE.JAVA_8) // Java 8 버전에서는 해당 테스트를 수행할 수 없다.
    public void assumeTest_5() {
        System.out.println("Hello JUnit5!");
    }

    @Test
    @DisplayName("특정 환경변수일 때만 해당 테스트를 수행한다.")
    // TEST_ENV 환경변수 값이 "LOCAL" 일 때 해당 테스트를 수행한다.
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
    // TEST_ENV 환경변수 값이 "DEVELOP" 일  때 해당 테스트를 수행하지 않는다.
    @DisabledIfEnvironmentVariable(named = "TEST_ENV", matches = "DEVELOP")
    public void assumeTest_6() {
        System.out.println("Good Bye, My Love.");
    }
}
