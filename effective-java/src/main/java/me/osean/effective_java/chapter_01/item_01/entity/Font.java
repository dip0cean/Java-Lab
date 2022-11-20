package me.osean.effective_java.chapter_01.item_01.entity;

public class Font {

    final String family;
    final int size;

    public Font(String family, int size) {
        this.family = family;
        this.size = size;
    }

    public String getFamily() {
        return this.family;
    }

    public int getSize() {
        return this.size;
    }
}
