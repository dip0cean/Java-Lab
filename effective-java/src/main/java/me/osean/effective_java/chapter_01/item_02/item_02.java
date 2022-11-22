package me.osean.effective_java.chapter_01.item_02;

import me.osean.effective_java.chapter_01.item_02.telescoping_constructor_pattern.NutritionFacts;

public class item_02 {

    public static void main(String[] args) {
        /*
            점층적 생성자 패턴
            - 클래스는 필수 속성과 선택 속성이 존재한다.
            때문에 여러 상황에 대응할 수 있도록 여러 시그니처의 생성자를 정의해야 하는데 이를 점층적 생성자 패턴이라고 한다.
            하지만 선택 속성이 늘어날수록 여러 때를 대비해야 하는 생성자 수가 늘어나는 문제가 있다.
         */
        NutritionFacts nutritionFacts_1 = new NutritionFacts(100, 1);
        NutritionFacts nutritionFacts_2 = new NutritionFacts(200, 1, 150, 15, 30, 10);
    }
}
