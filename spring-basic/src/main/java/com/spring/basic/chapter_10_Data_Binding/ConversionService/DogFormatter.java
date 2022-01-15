package com.spring.basic.chapter_10_Data_Binding.ConversionService;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

/**
 * Formatter 는 제네릭으로 한 개의 인자(대상이 되는 Object)만 받으며, Object 와 String 간의 변환을 담당한다.
 * 또한, 상태를 가지고 있지 않기 때문에 Thread-Safe 하기 때문에 @Component 로 등록해 다른 Spring Bean 객체를 주입 받아 사용 가능하다.
 */
@Component
public class DogFormatter implements Formatter<Dog> {

    @Override
    public Dog parse(String id, Locale locale) throws ParseException {
        return Dog
                .builder()
                .id(id)
                .name("또꼬망")
                .build();
    }

    @Override
    public String print(Dog dog, Locale locale) {
        return String.format("This dog name is %s", dog.getName());
    }
}
