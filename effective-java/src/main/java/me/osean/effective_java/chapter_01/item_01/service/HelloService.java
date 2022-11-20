package me.osean.effective_java.chapter_01.item_01.service;

public interface HelloService {

    // Before Java 8 > 메소드 선언
    String hello();

    // After Java 8 > public static Method 를 정의할 수 있게 되었다.
    static String hi() {
        prepareMessage();
        return "hi";
    }

    default String bye() {
        return "bye";
    }

    // After Java 9 > private static Method 를 정의할 수 있게 되었다.
    static private void prepareMessage() {
    }

    static String hi_2() {
        prepareMessage();
        return "hi";
    }

    static String hi_3() {
        prepareMessage();
        return "hi";
    }

    static String hi_4() {
        prepareMessage();
        return "hi";
    }
}
