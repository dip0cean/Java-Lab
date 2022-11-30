package me.osean.effective_java.chapter_01.item_03.singleton_pattern_04;

import me.osean.effective_java.chapter_01.item_03.singleton_pattern_01.Idol;
import me.osean.effective_java.chapter_01.item_03.singleton_pattern_01.Lesserafim;

public class Kara implements Idol {
    private static final Kara INSTANCE = new Kara();

    private Kara() {
    }

    public static Kara getInstance() {
        return INSTANCE;
    }


    @Override
    public void greetings() {
        System.out.println("Hello! My Luv, Kamilia! We're KARA!");
    }

    @Override
    public void sing() {
        System.out.println("나만의 Honey! Honey! Honey! 돌아서야 하니! 하니! 하니!");
    }

    public static void main(String[] args) {
        // 아래의 두 방법으로 구현한 Singleton Pattern 모두 동일한 단점을 가지게 된다.
        // 1. Reflection 을 통해 private Constructor 접근하여 인스턴스 생성
        // 2. 역직렬화를 통해 또 다른 인스턴스를 생성
        // 3. 클라이언트 테스트가 어려워진다.

        // static Instance 를 그대로 호출하는 경우
        Lesserafim lesserafim = Lesserafim.INSTANCE;
        lesserafim.greetings();

        // static Method 를 호출해 인스턴스를 가져오는 경우
        // 해당 방법으로 구현하는 경우, Singleton Pattern 을 해제하고자 할 때 클라이언트 레이어에서 별도의 수정 없이 코어 레이어에서만 변경해주면 된다.
        // 의존성을 줄일 수 있다.
        Kara kara = Kara.getInstance();
        kara.greetings();
    }
}
