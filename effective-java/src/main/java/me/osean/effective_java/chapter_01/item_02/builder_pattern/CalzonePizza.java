package me.osean.effective_java.chapter_01.item_02.builder_pattern;

public class CalzonePizza extends Pizza {
    private final boolean sauceInside;

    public static class Builder extends Pizza.Builder<Builder> {
        private final boolean sauceInside;

        public Builder(boolean sauceInside) {
            this.sauceInside = sauceInside;
        }

        @Override
        public CalzonePizza build() {
            return new CalzonePizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private CalzonePizza(Builder builder) {
        super(builder);
        this.sauceInside = builder.sauceInside;
    }
}
