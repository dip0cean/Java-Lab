package me.osean.effective_java.chapter_01.item_06;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class RomanNumeralTest {

    String romanNumeral = "MCMLXXVI";

    @Test
    void regexp() {
        assertTrue(() -> {
            int limit = 100;
            long slow = RomanNumeral.checkRomanNumeral(limit, romanNumeral, false);
            long fast = RomanNumeral.checkRomanNumeral(limit, romanNumeral, true);

            log.info("slow : {}, fast : {}, fast better than slow : {}", slow, fast, fast < slow);

            return fast < slow;
        });
    }

}