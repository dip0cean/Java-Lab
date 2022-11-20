package me.osean.effective_java.chapter_01.item_01;

import me.osean.effective_java.chapter_01.item_01.common.FontFactory;
import me.osean.effective_java.chapter_01.item_01.common.ProductType;
import me.osean.effective_java.chapter_01.item_01.entity.Character;
import me.osean.effective_java.chapter_01.item_01.entity.Font;
import me.osean.effective_java.chapter_01.item_01.entity.Product;
import me.osean.effective_java.chapter_01.item_01.entity.Wastebasket;
import me.osean.effective_java.chapter_01.item_01.service.HelloService;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.ServiceLoader;

public class Item_01 {
    public static void main(String[] args) {
        // 1. 이름을 지정 할 수 있다.
        Product.constructorHasName();

        // 2. 불필요한 인스턴스를 여러개 생성하지 않아도 된다. (Singleton / FlyWeight)
        Wastebasket wastebasket_1 = Wastebasket.of();
        Wastebasket wastebasket_2 = Wastebasket.of();
        wastebasket_1.isSameInstance(wastebasket_2);

        // 3. 변환 타입의 하위 타입 객체를 반환 할 수 있다.
        // 4. 입력 매개변수에 따라 상황에 맞는 다른 클래스의 객체를 반환 할 수 있다.
        // > 예를 들어 EnumSet 의 경우 Enum 이 64개 미만이면 RegularEnumSet 구현체를,
        // > 이상인 경우 JumboEnumSet 구현체를 인스턴스로 리턴한다.
        ProductType.printEnumSetClassType();

        // 5. 정적 팩토리 메소드를 적성하는 시점에서 꼭 반환할 객체의 클래스가 존재하지 않아도 된다.
        // > 서비스 제공자 프레임워크 (Java 에서는 ServiceLoader 를 제공)
        ServiceLoader<HelloService> productServiceServiceLoader = ServiceLoader.load(HelloService.class);
        productServiceServiceLoader.iterator().forEachRemaining(System.out::println);

        // Enum 은 JVM 에서 단 하나의 인스턴스만 가지게 된다. > Singleton Pattern 적용하기에 적합하다.
        // Enum 타입의 equals 연산은 == 비교 연산자를 이용하는 것이 좋다. > 이미 JVM 에 하나만 존재하기 때문에. > equals 는 NULL 을 체크하기 때문에 NullPointException 을 발생한다.
        // 때문에 Enum 을 비교할 때 == 를 사용하면 NULL 에 대한 NullPointException 발생을 방지 할 수 있다.
        Arrays.stream(ProductType.values()).forEach(System.out::println);
        EnumSet.allOf(ProductType.class).stream().map(ProductType::getDesc).forEach(System.out::println);
        System.out.println();

        // 같은 객체가 자주 사용된다. 매번 새로운 객체를 만들고 싶을까?
        // 아니, Singleton Pattern / FlyWeight Pattern / Caching 사용한다.
        // FlyWeight Patter?
        // > 객체를 가몁게 만들어 메모리 사용을 줄이는 패턴
        // > 자주 변하는 속성(외적인 속성, extrinsit)과 변하지 않는 속성(내적인 속정, intrinsit)을 분리하고 재사용하여 메모리 사용을 줄일 수 있다.
        Font font = FontFactory.getFont("san-serif:12");
        Character h = new Character('h', "black", font);
        Character e = new Character('e', "black", font);
        Character l = new Character('l', "black", font);
        Character o = new Character('o', "black", font);
        System.out.printf("%s%s%s%s%s\n", h, e, l, l, o);
    }
}
