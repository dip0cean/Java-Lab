package me.osean.effective_java.chapter_01.item_02.telescoping_constructor_pattern;

public class NutritionFacts {
    private final int servingSize; // (필수) 1회 제공량, ml
    private final int servings; // (필수) 총 n회 제공량, 회
    private final int calories; // (선택) 1회 제공량당
    private final int fat; // (선택) g/1회 제공량
    private final int sodium; // (선택) mg/1회 제공량
    private final int carbohydrate; // (선택) g/1회 제공량

    public NutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }
}
