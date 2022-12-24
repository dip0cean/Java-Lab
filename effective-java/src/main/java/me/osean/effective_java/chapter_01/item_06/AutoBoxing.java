package me.osean.effective_java.chapter_01.item_06;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutoBoxing {

    public static long sum(boolean isReference) {
        long start = System.nanoTime();
        if (isReference) {
            sumByReference();
        } else {
            sumByPrimitive();
        }
        long finish = System.nanoTime();

        return (finish - start) / 1_000_000;
    }

    private static void sumByReference() {
        Long target = 0L;
        for (long l = 0L; l <= Integer.MAX_VALUE; l++) {
            target += l;
        }
    }

    private static void sumByPrimitive() {
        long target = 0L;
        for (long l = 0L; l <= Integer.MAX_VALUE; l++) {
            target += l;
        }
    }
}
