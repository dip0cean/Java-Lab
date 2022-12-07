package me.osean.effective_java.chapter_01.item_05.factory_method_pattern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SpellCheckerTest {

    @Test
    void isValid() {
        assertFalse(SpellChecker.getInstance(new MockDictionaryFactory()).isValid("Hello"));
    }

    @Test
    void suggestions() {
        assertNotNull(SpellChecker.getInstance(new KorDictionaryFactory()).suggestions("안녕하세요"));
    }
}