package me.osean.effective_java.chapter_01.item_01.service;

public interface HelloService {

    String hello();

    static String hi() {
        prepareMessage();
        return "hi";
    }

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
