package me.osean.effective_java.chapter_01.item_06;

import java.util.function.IntConsumer;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class RomanNumeral {

    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3}(X[CL]|L?X{0,3})(T[XV]|V?I{0,3}))$");

    private static void isRomanNumeralSlow(String s) {
        s.matches("^(?=.)M*(C[MD]|D?C{0,3}(X[CL]|L?X{0,3})(T[XV]|V?I{0,3}))$");
    }

    private static void isRomanNumeralFast(String s) {
        ROMAN.matcher(s).matches();
    }

    private static void processing(int limit, IntConsumer consumer) {
        IntStream.range(0, limit)
                .forEach(consumer);
    }

    public static long checkRomanNumeral(int limit, String s, boolean isFast) {
        long start = System.nanoTime();
        if (isFast) {
            processing(limit, i -> isRomanNumeralFast(s));
        } else {
            processing(limit, i -> isRomanNumeralSlow(s));
        }
        long finish = System.nanoTime();

        return Math.subtractExact(finish, start);
    }
}
