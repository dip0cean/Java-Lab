package me.osean.effective_java.chapter_01.item_01.common;

import java.util.EnumSet;

public enum ProductType {
    FOOD("음식"), BEVERAGE("음료"), CLOTH("의류"), MACHINE("전자제품");

    private final String desc;

    ProductType(String desc) {
        this.desc = desc;
    }

    public static void printEnumSetClassType() {
        System.out.println(EnumSet.noneOf(ProductType.class).getClass());
        System.out.println();
    }

    public String getDesc() {
        return this.desc;
    }
}
