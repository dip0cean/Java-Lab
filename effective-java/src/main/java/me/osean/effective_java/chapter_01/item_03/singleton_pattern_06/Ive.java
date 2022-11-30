package me.osean.effective_java.chapter_01.item_03.singleton_pattern_06;

import me.osean.effective_java.chapter_01.item_03.singleton_pattern_01.Idol;

public class Ive implements Idol {
    private static final Ive INSTANCE = new Ive();

    private Ive() {
    }

    public static Ive getInstance() {
        return INSTANCE;
    }

    @Override
    public void greetings() {
        System.out.println("안녕하세요! IVE 입니다!");
    }

    @Override
    public void sing() {
        System.out.println("What's After Like? Yo ho & Ah HA~");
    }
}
