package me.osean.effective_java.chapter_01.item_06;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class AutoBoxingTest {

    @Test
    void autoBoxing() {
        assertTrue(() -> {
            long slow = AutoBoxing.sum(true);
            long fast = AutoBoxing.sum(false);

            // slow : 5933ms, fast : 668ms, fast better than slow : true
            log.info("slow : {}ms, fast : {}ms, fast better than slow : {}", slow, fast, fast < slow);

            return fast < slow;
        });
    }

}