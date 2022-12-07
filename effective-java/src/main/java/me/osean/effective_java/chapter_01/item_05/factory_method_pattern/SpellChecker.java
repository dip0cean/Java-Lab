package me.osean.effective_java.chapter_01.item_05.factory_method_pattern;

import me.osean.effective_java.chapter_01.item_05.dependency_injection.Dictionary;

import java.util.List;

public class SpellChecker {

    private static final SpellChecker SPELL_CHECKER = new SpellChecker();
    private Dictionary dictionary;

    private SpellChecker() {
    }

    public static SpellChecker getInstance(DictionaryFactory dictionaryFactory) {
        SPELL_CHECKER.dictionary = dictionaryFactory.getDictionary();
        return SPELL_CHECKER;
    }

    public boolean isValid(String word) {
        return dictionary.contains(word);
    }

    public List<String> suggestions(String typo) {
        return dictionary.closeWordsTo(typo);
    }
}
