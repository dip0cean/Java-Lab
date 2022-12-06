package me.osean.effective_java.chapter_01.item_05.dependency_injection;

import java.util.Collections;
import java.util.List;

public class KorDictionary implements Dictionary {

    private static final KorDictionary DICTIONARY = new KorDictionary();
    private static final String KOR_REGEX = ".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*";

    private KorDictionary() {
    }

    public static KorDictionary getInstance() {
        return DICTIONARY;
    }

    @Override
    public boolean contains(String word) {
        return word.matches(KOR_REGEX);
    }

    @Override
    public List<String> closeWordsTo(String typo) {
        return Collections.emptyList();
    }
}
