package me.osean.effective_java.chapter_01.item_03.singleton_pattern_06;

import me.osean.effective_java.chapter_01.item_03.singleton_pattern_01.Idol;

@FunctionalInterface
public interface Celebrity<T extends Idol> {

    T get();
}
