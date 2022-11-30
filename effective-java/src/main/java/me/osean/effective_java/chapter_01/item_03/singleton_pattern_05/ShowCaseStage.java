package me.osean.effective_java.chapter_01.item_03.singleton_pattern_05;

import me.osean.effective_java.chapter_01.item_03.singleton_pattern_01.Lesserafim;
import me.osean.effective_java.chapter_01.item_03.singleton_pattern_04.Kara;

public class ShowCaseStage<T> {

    private static final ShowCaseStage<Object> INSTANCE = new ShowCaseStage<>();

    private ShowCaseStage() {
    }

    @SuppressWarnings("unchecked")
    public static <T> ShowCaseStage<T> getInstance() {
        return (ShowCaseStage<T>) INSTANCE;
    }

    public void perform(T t) {
        System.out.println(t.toString());
    }

    public static void main(String[] args) {
        ShowCaseStage<Lesserafim> lesserafimShowCaseStage = ShowCaseStage.getInstance();
        ShowCaseStage<Kara> karaShowCaseStage = ShowCaseStage.getInstance();

        lesserafimShowCaseStage.perform(Lesserafim.INSTANCE);
        karaShowCaseStage.perform(Kara.getInstance());

        System.out.println(lesserafimShowCaseStage);
        System.out.println(karaShowCaseStage);
        // Generic Singleton 의 경우, HashCode 는 같아도 Generic Type 이 다르기 때문에 == 비교 연산자는 사용 할 수 없다.
        // 때문에, equals() 를 이용해서 객체 비교를 수행해야 한다.
        System.out.println(lesserafimShowCaseStage.equals(karaShowCaseStage));
//        System.out.println(lesserafimShowCaseStage == karaShowCaseStage);
    }
}
