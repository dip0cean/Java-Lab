package me.osean.effective_java.chapter_01.item_01.entity;

/**
 * FlyWeight Pattern Example
 */
public class Character {
    private final char value;
    private final String color;
    private final Font font;

    public Character(char value, String color, Font font) {
        this.value = value;
        this.color = color;
        this.font = font;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
