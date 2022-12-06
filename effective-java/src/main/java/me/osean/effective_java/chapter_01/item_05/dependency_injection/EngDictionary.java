package me.osean.effective_java.chapter_01.item_05.dependency_injection;

import java.util.Collections;
import java.util.List;

public class EngDictionary implements Dictionary {

    private static final EngDictionary DICTIONARY = new EngDictionary();
    private static final String ENG_REGEX = ".*[a-zA-z]+.*";

    private EngDictionary() {
    }

    public static EngDictionary getInstance() {
        return DICTIONARY;
    }

    @Override
    public boolean contains(String word) {
        return word.matches(ENG_REGEX);
    }

    @Override
    public List<String> closeWordsTo(String typo) {
        return Collections.emptyList();
    }
}
