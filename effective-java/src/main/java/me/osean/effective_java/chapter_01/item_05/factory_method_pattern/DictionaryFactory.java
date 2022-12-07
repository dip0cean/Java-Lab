package me.osean.effective_java.chapter_01.item_05.factory_method_pattern;

import me.osean.effective_java.chapter_01.item_05.dependency_injection.Dictionary;

/**
 * Factory Method Pattern
 * 새로운 클래스를 제공하는 팩토리를 추가하더라도, 팩토리를 사용하는 클라이언트 코드는 변경할 필요가 없다. (인터페이스적인 시각, 추상화)
 */
public interface DictionaryFactory {

    Dictionary getDictionary();
}
