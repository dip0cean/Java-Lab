package me.osean.effective_java.chapter_01.item_06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringsTest {

    private Strings strings;

    @BeforeEach
    void beforeEach() {
        strings = new Strings();
    }

    @Test
    void isEquals() {
        assertAll(
                // "Hello" 와 new String("Hello") 는 서로 다른 인스턴스이기 때문에 동등 비교 연산 시 False 를 리턴한다.
                () -> assertFalse(() -> strings.getExample_1() == strings.getExample_2()),
                // 때문에 동일한 문자열인지 비교하기 위해서는 equals() 메소드를 이용해 문자열 비교하는 것이 좋다.
                () -> assertEquals(strings.getExample_1(), strings.getExample_2()),
                // 문자열은 JVM 내 String Constant Pool 에 HashMap 과 비슷한 형식으로 저장되어 동일한 문자열인 경우 해당 동일한 메모리 주소를 갖는다.
                // 때문에 example_1 과 example_3 는 같은 메모리 주소를 참조하고 있어 동등 비교 연산 시 True 를 리턴한다.
                () -> assertTrue(() -> strings.getExample_1() == strings.getExample_3()),
                () -> assertEquals(strings.getExample_1(), strings.getExample_3())
        );
    }
}