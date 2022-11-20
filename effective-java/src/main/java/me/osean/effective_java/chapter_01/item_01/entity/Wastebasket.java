package me.osean.effective_java.chapter_01.item_01.entity;

public class Wastebasket {

    private static final Wastebasket WASTEBASKET = new Wastebasket();

    private Wastebasket() {
    }

    public static Wastebasket of() {
        return WASTEBASKET;
    }

    public void isSameInstance(Wastebasket wastebasket) {
        System.out.printf("1. %s \n2. %s \nisSame? %s\n\n", this.hashCode(), wastebasket.hashCode(), (this.equals(wastebasket)));
    }
}
