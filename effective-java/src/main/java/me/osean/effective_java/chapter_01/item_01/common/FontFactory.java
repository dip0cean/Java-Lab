package me.osean.effective_java.chapter_01.item_01.common;

import me.osean.effective_java.chapter_01.item_01.entity.Font;

import java.util.HashMap;
import java.util.Map;

public class FontFactory {

    private static Map<String, Font> cache = new HashMap<>();

    public static Font getFont(String font) {
        if (cache.containsKey(font)) {
            return cache.get(font);
        } else {
            String[] split = font.split(":");
            Font newFont = new Font(split[0], Integer.parseInt(split[1]));
            cache.put(font, newFont);
            return newFont;
        }
    }
}
