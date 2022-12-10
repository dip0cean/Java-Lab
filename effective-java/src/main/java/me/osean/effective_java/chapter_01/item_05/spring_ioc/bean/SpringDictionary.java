package me.osean.effective_java.chapter_01.item_05.spring_ioc.bean;

import me.osean.effective_java.chapter_01.item_05.dependency_injection.Dictionary;

import java.util.List;

public class SpringDictionary implements Dictionary {
    @Override
    public boolean contains(String word) {
        System.out.println("contains " + word);
        return false;
    }

    @Override
    public List<String> closeWordsTo(String typo) {
        return null;
    }
}
