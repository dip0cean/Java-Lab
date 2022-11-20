package me.osean.effective_java.chapter_06.item_34;

import java.util.Arrays;

public class Item_34 {
    public static void main(String[] args) {
        // Item 34 > int 상수 대신 열거 타입을 사용하라

        // 1. int 상수를 사용할 경우 NameSpace 를 제공하지 않기 때문에 접두어를 사용해 상수 간 이름 충돌을 방지해야 하는 문제가 발생한다.
        // Test 1. int 상수를 이용하는 경우
        int appleFuji = Fruit.APPLE_FUJI;
        int orangeNavel = Fruit.ORANGE_NAVEL;
        // 단점 1. int 상수는 NameSpace 가 존재하지 않아 정수값을 그대로 반환하기에 해당 값이 어떤 의미를 가지는지 파악하기 어렵다.
        // 단점 2. int 상수간 동등 비교 연산(==)을 수행 시, 의미가 다른 상수더라도 정수값이 같다면 True 를 반환하며 컴파일러는 이에 대한 아무런 경고 메세지를 발생하지 않는다.
        System.out.printf("%s 는 오렌지인가? %s\n\n", appleFuji, (appleFuji == orangeNavel));

        // Test 2. 열거 타입(Enum) 을 사용하는 경우
        // 장점 1. 열거 타입은 Name Space 를 가지기 때문에 어떤 의미를 가지는지 바로 이해할 수 있다.
        // 장점 2. 열거 타입은 Type Safe 하기 때문에 동일한 이름의 열거 타입이 존재할 수 있다.
        // 장점 3. 동일한 이름의 열거 타입이라도 컴파일타임에서 타입 안전성을 제공하기 때문에 미리 파악할 수 있다.
        Arrays.stream(Idol.FemaleIdol.values()).forEach(idol -> System.out.printf("%s 는 남자 아이돌인가? %s\n", idol, Idol.checkSex(idol, Idol.MaleIdol.class)));
        System.out.println();
        Arrays.stream(Idol.MaleIdol.values()).forEach(idol -> System.out.printf("%s 는 남자 아이돌인가? %s\n", idol, Idol.checkSex(idol, Idol.MaleIdol.class)));
        System.out.println();


        double earthWeight = Double.parseDouble("185");
        double mass = earthWeight / Plant.EARTH.surfaceGravity();
        Arrays.stream(Plant.values()).forEach(plant -> System.out.printf("%s 에서의 무게는 %f 이다.%n", plant, plant.surfaceGravityWeight(mass)));
    }
}
