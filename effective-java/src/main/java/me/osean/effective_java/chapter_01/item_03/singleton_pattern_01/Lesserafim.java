package me.osean.effective_java.chapter_01.item_03.singleton_pattern_01;

public class Lesserafim implements Idol {
    public static final Lesserafim INSTANCE = new Lesserafim();

    private Lesserafim() {
    }

    @Override
    public void greetings() {
        System.out.println("Hello! We're LE SSERAFIM!");
    }

    @Override
    public void sing() {
        System.out.println("Anti-ti-ti-ti-ti fragile!");
    }
}
