package me.osean.effective_java.chapter_01.item_05.dependency_injection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SpellCheckerTest {

    @Test
    void isEqLanguage() {
        SpellChecker korChecker = new SpellChecker(KorDictionary.getInstance());
        assertTrue(korChecker.isValid("안녕하세요."));

        SpellChecker engChecker = new SpellChecker(EngDictionary.getInstance());
        assertTrue(engChecker.isValid("Hello"));
    }

    @Test
    void supplier() {
        SpellChecker korChecker = new SpellChecker(KorDictionary::getInstance);
    }
}