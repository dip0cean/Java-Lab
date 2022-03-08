package com.spring.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class AssertXXXTest {

    @Test
    @DisplayName("기본적인 Assert 테스트")
    public void assertTest_1() {
        Study study = new Study(-10, "Test Study");
        // Assert 묶기 > assertAll 내부에 있는 assert 문 중에서 실패한 모든 assert 문을 체크할 수 있다.
        assertAll(
                // NotNull Check
                () -> assertNotNull(study),
                // Equals Check
                () -> {
                    study.setStatus(Study.Status.DRAFT);
                    assertEquals(Study.Status.DRAFT, study.getStatus(), "study 의 상태를 \"Study.Status.DRAFT\" 를 기본값으로 설정해주세요.");
                },
                // True Check
                () -> {
                    study.setLimit(10);
                    assertTrue(study.getLimit() > 1, "study 최대 참여 인원은 1명 이상이여야 합니다.");
                }
        );
    }

    @Test
    @DisplayName("AssertThrows 테스트")
    public void assertTest_2() {
        NullPointerException npe = assertThrows(NullPointerException.class, () -> new Study(10, ""));
        assertEquals("스터디명을 입력해주세요.", npe.getMessage());
    }

    @Test
    @DisplayName("AssertTimeout 테스트")
    public void assertTest_3() {
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study();
            Thread.sleep(50);
        });
    }
}
