package me.osean.effective_java.chapter_01.item_02.builder_pattern;

public class Sandwich {
    public enum Bread {OAT_BREAD, FLAT_BREAD, BAGUETTE}

    public enum Meat {BEEF, PORK, CHICKEN}

    public enum Cheese {AMERICAN_CHEESE, MOZZARELLA_CHEESE, CHEDDAR_CHEESE}

    public enum Bevarage {COKE, SPRITE, MILK, COFFEE}

    public enum SideMenu {CHIPS, SOUP, COOKIE}

    private final Bread bread;
    private final Meat meat;
    private final Cheese cheese;
    private final Bevarage bevarage;
    private final SideMenu sideMenu;

    public static class Builder {
        private final Bread bread;
        private final Meat meat;
        private Cheese cheese;
        private Bevarage bevarage;
        private SideMenu sideMenu;

        public Builder(Bread bread, Meat meat) {
            this.bread = bread;
            this.meat = meat;
        }

        public Builder cheese(Cheese cheese) {
            this.cheese = cheese;
            return this;
        }

        public Builder beverage(Bevarage bevarage) {
            this.bevarage = bevarage;
            return this;
        }

        public Builder sideMenu(SideMenu sideMenu) {
            this.sideMenu = sideMenu;
            return this;
        }

        public Sandwich build() {
            return new Sandwich(this);
        }
    }

    private Sandwich(Builder builder) {
        this.bread = builder.bread;
        this.meat = builder.meat;
        this.cheese = builder.cheese;
        this.bevarage = builder.bevarage;
        this.sideMenu = builder.sideMenu;
    }
}
