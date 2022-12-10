package me.osean.effective_java.chapter_01.item_05.spring_ioc.bean;

import me.osean.effective_java.chapter_01.item_05.dependency_injection.Dictionary;

import java.util.List;

public class SpellChecker {

    private Dictionary dictionary;

    public SpellChecker(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public boolean isValid(String word) {
        return dictionary.contains(word);
    }

    public List<String> suggestions(String type) {
        return dictionary.closeWordsTo(type);
    }
}
