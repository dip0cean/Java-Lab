package me.osean.effective_java.chapter_01.item_05.dependency_injection;

import java.util.List;

public interface Dictionary {

    boolean contains(String word);

    List<String> closeWordsTo(String typo);
}
