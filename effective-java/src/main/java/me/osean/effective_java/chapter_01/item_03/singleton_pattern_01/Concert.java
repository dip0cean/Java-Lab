package me.osean.effective_java.chapter_01.item_03.singleton_pattern_01;

public class Concert {
    private boolean lightOn;
    private boolean mainStateOpen;

    private final Idol idol;

    public Concert(Idol idol) {
        this.idol = idol;
    }

    public void perform() {
        this.mainStateOpen = true;
        this.lightOn = true;
        this.idol.greetings();
        this.idol.sing();
    }

    public boolean isLightOn() {
        return this.lightOn;
    }

    public boolean isMainStateOpen() {
        return this.mainStateOpen;
    }
}
