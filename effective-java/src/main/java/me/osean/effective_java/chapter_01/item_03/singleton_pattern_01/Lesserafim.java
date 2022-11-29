package me.osean.effective_java.chapter_01.item_03.singleton_pattern_01;

public class Lesserafim implements Idol {
    public static final Lesserafim INSTANCE = new Lesserafim();
    // 최초 생성 여부 플래그를 통해서 추가적인 인스턴스가 생성되는 것을 방어하도록 한다.
    private static boolean created;

    private Lesserafim() {
        // 생성자 호출 시, created 필드의 False 여부 확인
        if (created) {
            throw new UnsupportedOperationException("Can't be created by Constructor.");
        }

        // 처음 생성하는 경우, created 필드를 True 로 초기화
        created = true;
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
