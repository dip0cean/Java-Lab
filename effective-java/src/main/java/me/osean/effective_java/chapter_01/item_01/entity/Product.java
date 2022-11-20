package me.osean.effective_java.chapter_01.item_01.entity;

import me.osean.effective_java.chapter_01.item_01.common.ProductType;

import java.util.UUID;
import java.util.stream.Stream;

public class Product {

    private final String id = UUID.randomUUID().toString();
    private ProductType type;
    private String name;
    private int price;

    /**
     * 1. 정적 팩토리 메소드를 사용하면 의도에 따른 이름을 가질 수 있다.
     * 여러 매개변수를 인자로 받는 생성자를 통해 인스턴스를 생성할 경우, 어떤 의미로 사용되는지 파악하기 어렵다.
     * 하지만 정적 팩토리 메소드를 사용하면 의도에 맞는 이름을 지정할 수 있고, 개발자는 그 의미를 쉽게 파악하여 빠르게 개발이 가능하다.
     */
    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(ProductType type, String name) {
        this.type = type;
        this.name = name;
    }

    public Product(ProductType type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public static Product newEmptyInstance() {
        return new Product();
    }

    public static Product newInstanceWithName(String name) {
        return new Product(name);
    }

    public static Product newInstanceWithTypeAndName(ProductType type, String name) {
        return new Product(type, name);
    }

    public static Product newInstance(ProductType type, String name, int price) {
        return new Product(type, name, price);
    }


    public static void constructorHasName() {
        ProductType type = ProductType.FOOD;
        String name = "ramen";
        int price = 1000;

        // 1. 이름을 지정 할 수 있다.
        // 생성자를 이용할 경우 의도를 파악하기 힘들다.
        Product product_1 = new Product();
        Product product_2 = new Product(name);
        Product product_3 = new Product(type, name);
        Product product_4 = new Product(type, name, price);

        // 정적 팩토리 메소드를 사용할 경우 의도를 파악하기 쉽다
        Product product_5 = Product.newEmptyInstance();
        Product product_6 = Product.newInstanceWithName(name);
        Product product_7 = Product.newInstanceWithTypeAndName(type, name);
        Product product_8 = Product.newInstance(type, name, price);

        Stream.of(product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8)
                .forEach(System.out::println);
        System.out.println();
    }
}
