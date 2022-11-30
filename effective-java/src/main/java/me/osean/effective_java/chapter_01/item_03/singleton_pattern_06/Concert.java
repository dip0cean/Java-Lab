package me.osean.effective_java.chapter_01.item_03.singleton_pattern_06;

import me.osean.effective_java.chapter_01.item_03.singleton_pattern_01.Idol;

public class Concert {

    public void startShow(Celebrity<Idol> celebrity) {
        Idol idol = celebrity.get();
        idol.greetings();
        idol.sing();
    }

    public static void main(String[] args) {
        Concert concert = new Concert();
        concert.startShow(Ive::getInstance);
    }
}
