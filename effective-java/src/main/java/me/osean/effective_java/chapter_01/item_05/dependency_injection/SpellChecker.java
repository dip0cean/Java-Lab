package me.osean.effective_java.chapter_01.item_05.dependency_injection;

import java.util.List;
import java.util.function.Supplier;

/**
 * 사용하는 자원에 따라 동작이 달라지는 클래스는 정적 유틸리티 클래스나 싱글턴 패턴이 적합하지 않다.
 * 왜? 객체의 속성값이 상황에 따라 바뀌기도 하고, 싱글턴의 경우 하나의 객체가 여러 스레드에서 사용되기 때문에 일관성을 유지해야 한다.
 * <p>
 * 때문에 이러한 클래스들은 의존 객체 주입 방식을 이용하는 것이 효율적이다. (Dependency Injection)
 * 새로운 인스턴스를 생성할 때 필요한 자원을 생성한 객체의 속성값으로 넘겨주는 방식.
 * 해당 방식의 변형으로 생성자에 자원 팩토리를 넘겨줄 수 있다.
 * 의존 객체 주입을 사용하면 클래스의 유연성, 재사용성, 테스트 용이성을 개선할 수 있다.
 */
public class SpellChecker {

    private final Dictionary dictionary;

    public SpellChecker(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public SpellChecker(Supplier<? extends Dictionary> dictionarySupplier) {
        this.dictionary = dictionarySupplier.get();
    }

    public boolean isValid(String word) {
        return dictionary.contains(word);
    }

    public List<String> suggestions(String typo) {
        return dictionary.closeWordsTo(typo);
    }
}
