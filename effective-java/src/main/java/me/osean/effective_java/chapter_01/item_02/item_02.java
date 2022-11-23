package me.osean.effective_java.chapter_01.item_02;

import me.osean.effective_java.chapter_01.item_02.builder_pattern.CalzonePizza;
import me.osean.effective_java.chapter_01.item_02.builder_pattern.NyPizza;
import me.osean.effective_java.chapter_01.item_02.builder_pattern.Pizza;
import me.osean.effective_java.chapter_01.item_02.builder_pattern.Sandwich;
import me.osean.effective_java.chapter_01.item_02.java_beans_pattern.User;
import me.osean.effective_java.chapter_01.item_02.telescoping_constructor_pattern.NutritionFacts;

import java.time.LocalDate;

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

        /*
            Java Beans Pattern
            - 선택 속성에 대한 Setter 를 사용해 여러 경우에 유연하게 대응할 수 있도록 한다.
            Setter 로 인해 필수값이 무분별하게 사용되므로 컨시스턴스가 깨지는 문제도 동반되며,
            어떤 속성까지 필수적으로 값을 세팅해야 하는지 문서나 주석을 확인하지 않으면 알 수 없다. 즉, 소통의 문제가 발생해 여러 문제를 야기할 수 있다.
            이러한 문제들 때문에 선택 속성에 대해서만 Setter 를 정의하도록 하는 방법을 이용할 수 있지만 이는 불변 객체로 만들 수가 없다는 문제가 해결되지 않는다.
         */
        User user = new User();
        user.setId("newId");
        user.setPassword("newPassword");
        user.setNickname("newNickname");
        user.setSex(User.Sex.MALE);
        user.setBirth(LocalDate.of(1995, 3, 4));

        /*
            Builder Pattern
            - 부모 클래스와 멤버 필드를 동일하게 가지는 Builder 클래스를 부모 클래스 내부에 정의해 Builder 클래스를 이용해 부모 클래스의 인스턴스를 생성한다.
            필수 속성들은 Builder 의 생성자로 주입하고, 나머지 선택 속성들은 Builder 의 Setter 메소드를 이용해 상황에 맞게 유연하게 사용 가능하다.
            이러한 특성 때문에 점층적 생성자 패턴에서 나타나는 문제인 선택 속성에 대한 무분별한 생성자 정의나 Java Beans 의 Setter 의 객체의 불변성을 깨트리는 문제를 극복할 수 있다.
            Builder 는 Lombok 의 @Builder 를 사용하면 작성해야하는 코드를 비약적으로 줄일 수 있으나, 필수 속성을 지정해 Builder 생성자에 주입할 수 있는 설정이 없다는 단점이 존재한다.
         */
        Sandwich porkOatSandwich = new Sandwich
                .Builder(Sandwich.Bread.OAT_BREAD, Sandwich.Meat.PORK)
                .build();

        Sandwich beefFlatBreadSandwichSet = new Sandwich
                .Builder(Sandwich.Bread.FLAT_BREAD, Sandwich.Meat.BEEF)
                .cheese(Sandwich.Cheese.MOZZARELLA_CHEESE)
                .beverage(Sandwich.Bevarage.COFFEE)
                .sideMenu(Sandwich.SideMenu.COOKIE)
                .build();

        /*
            계층적 구조에서 Builder Pattern 을 적용
            추상 클래스의 추상 Builder 클래스를 정의하고, 상속하는 클래스에서 해당 추상 Builder 를 구현하면서 재귀적인 타입 한정을 이용하도록 한다.
         */
        Pizza largeNyPizza = new NyPizza.Builder(NyPizza.Size.LARGE).build();
        Pizza mediumNyPizzaWithSausage = new NyPizza.Builder(NyPizza.Size.MEDIUM).addTopping(Pizza.Topping.SAUSAGE).build();
        Pizza smallNyPizzaWithMushroom = new NyPizza.Builder(NyPizza.Size.SMALL).addTopping(Pizza.Topping.MUSHROOM).build();

        Pizza largeCalzonePizza = new CalzonePizza.Builder().build();
        Pizza mediumCalzonePizzaWithHam = new CalzonePizza.Builder().addTopping(Pizza.Topping.HAM).build();
        Pizza smallCalzonePizzaWithOnion = new CalzonePizza.Builder().addTopping(Pizza.Topping.ONION).sauceInside().build();
    }
}
